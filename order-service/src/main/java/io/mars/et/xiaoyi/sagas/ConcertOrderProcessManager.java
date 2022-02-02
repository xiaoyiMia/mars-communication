package io.mars.et.xiaoyi.sagas;

import io.mars.et.xiaoyi.commands.*;
import io.mars.et.xiaoyi.common.ID;
import io.mars.et.xiaoyi.common.IdGenerator;
import io.mars.et.xiaoyi.events.ConcertOrderPlacedEvent;
import io.mars.et.xiaoyi.events.ReservationAcceptedEvent;
import io.mars.et.xiaoyi.events.ReservationRejectedEvent;
import io.mars.et.xiaoyi.exceptions.InvalidOperationException;
import org.axonframework.commandhandling.gateway.CommandGateway;
import org.axonframework.deadline.DeadlineManager;
import org.axonframework.deadline.annotation.DeadlineHandler;
import org.axonframework.modelling.saga.EndSaga;
import org.axonframework.modelling.saga.SagaEventHandler;
import org.axonframework.modelling.saga.SagaLifecycle;
import org.axonframework.modelling.saga.StartSaga;
import org.axonframework.spring.stereotype.Saga;
import org.springframework.beans.factory.annotation.Autowired;

import java.time.Duration;

@Saga
public class ConcertOrderProcessManager {
  private ID orderId;
  private ProcessState state = ProcessState.NOT_START;

  @Autowired
  private transient CommandGateway commandGateway;
  @Autowired
  private transient IdGenerator idGenerator;
  @Autowired
  private transient DeadlineManager deadlineManager;

  @StartSaga
  @SagaEventHandler(associationProperty = "orderId")
  public void handler(ConcertOrderPlacedEvent message) {
    if(this.state == ProcessState.NOT_START) {
      this.orderId = message.getOrderId();
      this.state = ProcessState.AWAITING_RESERVATION_CONFIRMATION;

      ID reservationId = idGenerator.generateId();
      SagaLifecycle.associateWith("reservationId", reservationId.toString());

      // Assume all order items are seat reservations for now.
      commandGateway.send(new MakeSeatReservationCommand(
          message.getConcertId(),
          reservationId,
          message.getSeatReservations().stream().mapToInt(ConcertOrderPlacedEvent.AbstractOrderItem::getQuantity).sum())
      );
    } else {
      throw new InvalidOperationException();
    }
  }

  @SagaEventHandler(associationProperty = "reservationId")
  public void handler(ReservationAcceptedEvent message) {
    if(this.state == ProcessState.AWAITING_RESERVATION_CONFIRMATION) {
      this.state = ProcessState.AWAITING_PAYMENT;
      commandGateway.send(new MarkOrderAsBookedCommand(this.orderId));
      // Schedule a deadline to expire the order
      deadlineManager.schedule(
          Duration.ofMinutes(OrderPaymentDeadline.EXP_MINS),
          OrderPaymentDeadline.DEADLINE_NAME,
          new OrderPaymentDeadline(message.getConferenceId(), message.getReservationId())
      );
    } else {
      throw new InvalidOperationException();
    }
  }

  @EndSaga
  @SagaEventHandler(associationProperty = "reservationId")
  public void handler(ReservationRejectedEvent message) {
    if(this.state == ProcessState.AWAITING_RESERVATION_CONFIRMATION) {
      this.state = ProcessState.COMPLETED;
      commandGateway.send(new RejectOrderCommand(this.orderId));
    }
  }

  @EndSaga
  @DeadlineHandler(deadlineName = OrderPaymentDeadline.DEADLINE_NAME)
  public void on(OrderPaymentDeadline deadlinePayload) {
    if(this.state == ProcessState.AWAITING_PAYMENT) {
      this.state = ProcessState.COMPLETED;

      commandGateway.send(
          new CancelSeatReservationCommand(deadlinePayload.getConcertId(), deadlinePayload.getReservationId())
      );
      commandGateway.send(new RejectOrderCommand(this.orderId));
    } else {
      throw new InvalidOperationException();
    }
  }

  enum ProcessState {
    NOT_START, AWAITING_RESERVATION_CONFIRMATION, AWAITING_PAYMENT, COMPLETED
  }
}
