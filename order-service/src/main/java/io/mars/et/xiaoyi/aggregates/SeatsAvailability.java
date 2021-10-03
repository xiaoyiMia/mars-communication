package io.mars.et.xiaoyi.aggregates;

import io.mars.et.xiaoyi.commands.MakeSeatReservationCommand;
import io.mars.et.xiaoyi.events.ReservationAcceptedEvent;
import io.mars.et.xiaoyi.events.ReservationRejectedEvent;
import lombok.Data;
import org.axonframework.commandhandling.CommandHandler;
import org.axonframework.modelling.command.AggregateIdentifier;
import org.axonframework.spring.stereotype.Aggregate;

import static org.axonframework.modelling.command.AggregateLifecycle.apply;

@Aggregate
@Data
public class SeatsAvailability {
  @AggregateIdentifier
  private String conferenceId;
  private int remainingSeats;

  @CommandHandler
  public SeatsAvailability(MakeSeatReservationCommand command) {
    if (command.getNumberOfSeats() > this.remainingSeats) {
      apply(new ReservationRejectedEvent(command.getReservationId(), command.getConferenceId()));
    } else {
      // TODO: add pending reservations
      this.remainingSeats -= command.getNumberOfSeats();
      apply(new ReservationAcceptedEvent(command.getReservationId(), command.getConferenceId()));
    }
  }

  protected SeatsAvailability() {
  }
}
