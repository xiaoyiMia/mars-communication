package io.mars.et.xiaoyi.sagas;

import io.mars.et.xiaoyi.commands.MakeSeatReservationCommand;
import io.mars.et.xiaoyi.commands.MarkOrderAsBookedCommand;
import io.mars.et.xiaoyi.commands.RejectOrderCommand;
import io.mars.et.xiaoyi.common.ID;
import io.mars.et.xiaoyi.common.IdGenerator;
import io.mars.et.xiaoyi.events.ConcertOrderPlacedEvent;
import io.mars.et.xiaoyi.events.ReservationAcceptedEvent;
import io.mars.et.xiaoyi.events.ReservationRejectedEvent;
import io.mars.et.xiaoyi.exceptions.InvalidOperationException;
import org.axonframework.commandhandling.gateway.CommandGateway;
import org.axonframework.modelling.saga.SagaEventHandler;
import org.axonframework.modelling.saga.SagaLifecycle;
import org.axonframework.modelling.saga.StartSaga;
import org.axonframework.spring.stereotype.Saga;
import org.springframework.beans.factory.annotation.Autowired;

@Saga
public class ConcertOrderProcessManager {

  private ID orderId;
  private ProcessState state = ProcessState.NOT_START;

  @Autowired
  private transient CommandGateway commandGateway;
  @Autowired
  private transient IdGenerator idGenerator;

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
      // TODO: deadline handler
    } else {
      throw new InvalidOperationException();
    }
  }

  @SagaEventHandler(associationProperty = "reservationId")
  public void handler(ReservationRejectedEvent message) {
    if(this.state == ProcessState.AWAITING_RESERVATION_CONFIRMATION) {
      this.state = ProcessState.COMPLETED;
      commandGateway.send(new RejectOrderCommand(this.orderId));
    } else {
      throw new InvalidOperationException();
    }
  }


  enum ProcessState {
    NOT_START, AWAITING_RESERVATION_CONFIRMATION, AWAITING_PAYMENT, COMPLETED
  }
}
