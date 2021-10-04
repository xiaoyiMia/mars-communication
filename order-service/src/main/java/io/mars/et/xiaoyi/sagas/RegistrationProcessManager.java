package io.mars.et.xiaoyi.sagas;

import io.mars.et.xiaoyi.commands.MakeSeatReservationCommand;
import io.mars.et.xiaoyi.commands.MarkOrderAsBookedCommand;
import io.mars.et.xiaoyi.events.OrderPlacedEvent;
import io.mars.et.xiaoyi.events.ReservationAcceptedEvent;
import io.mars.et.xiaoyi.events.ReservationRejectedEvent;
import io.mars.et.xiaoyi.exceptions.InvalidOperationException;
import org.axonframework.commandhandling.gateway.CommandGateway;
import org.axonframework.modelling.saga.SagaEventHandler;
import org.axonframework.modelling.saga.SagaLifecycle;
import org.axonframework.modelling.saga.StartSaga;
import org.axonframework.spring.stereotype.Saga;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.UUID;

@Saga
public class RegistrationProcessManager {

  private String orderId;
  private ProcessState state = ProcessState.NOT_START;

  @Autowired
  private transient CommandGateway commandGateway;

  @StartSaga
  @SagaEventHandler(associationProperty = "orderId")
  public void handler(OrderPlacedEvent message) {
    if(this.state == ProcessState.NOT_START) {
      this.orderId = message.getOrderId();
      this.state = ProcessState.AWAITING_RESERVATION_CONFIRMATION;

      SagaLifecycle.associateWith("conferenceId", message.getConferenceId());

      String reservationId = UUID.randomUUID().toString();
      commandGateway.send(new MakeSeatReservationCommand(
          message.getConferenceId(),
          reservationId,
          message.getSeats().stream().mapToInt(OrderPlacedEvent.Seat::getQuantity).sum())
      );
    } else {
      throw new InvalidOperationException();
    }
  }

  @SagaEventHandler(associationProperty = "orderId")
  public void handler(ReservationAcceptedEvent message) {
    if(this.state == ProcessState.AWAITING_RESERVATION_CONFIRMATION) {
      this.state = ProcessState.AWAITING_PAYMENT;
      commandGateway.send(new MarkOrderAsBookedCommand(this.orderId));
      // TODO: deadline handler
    } else {
      throw new InvalidOperationException();
    }
  }

  @SagaEventHandler(associationProperty = "orderId")
  public void handler(ReservationRejectedEvent message) {
    if(this.state == ProcessState.AWAITING_RESERVATION_CONFIRMATION) {
      this.state = ProcessState.COMPLETED;
      commandGateway.send(new MarkOrderAsBookedCommand(this.orderId));
      // TODO: deadline handler
    } else {
      throw new InvalidOperationException();
    }
  }


  enum ProcessState {
    NOT_START, AWAITING_RESERVATION_CONFIRMATION, AWAITING_PAYMENT, COMPLETED
  }
}
