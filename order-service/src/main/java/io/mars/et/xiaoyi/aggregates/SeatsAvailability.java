package io.mars.et.xiaoyi.aggregates;

import io.mars.et.xiaoyi.commands.MakeSeatReservationCommand;
import io.mars.et.xiaoyi.common.ID;
import io.mars.et.xiaoyi.events.ReservationAcceptedEvent;
import io.mars.et.xiaoyi.events.ReservationRejectedEvent;
import lombok.Data;
import org.axonframework.commandhandling.CommandHandler;
import org.axonframework.eventsourcing.EventSourcingHandler;
import org.axonframework.modelling.command.AggregateIdentifier;
import org.axonframework.spring.stereotype.Aggregate;

import static org.axonframework.modelling.command.AggregateLifecycle.apply;

@Aggregate
@Data
public class SeatsAvailability {
  @AggregateIdentifier
  private ID conferenceId;
  private int remainingSeats;

  @CommandHandler
  public SeatsAvailability(MakeSeatReservationCommand command) {
    // TODO: multi-threading??? Distributed system???
    if (command.getNumberOfSeats() > this.remainingSeats) {
      apply(new ReservationRejectedEvent(command.getReservationId(), command.getConferenceId()));
    } else {
      apply(new ReservationAcceptedEvent(command.getReservationId(), command.getConferenceId(), command.getNumberOfSeats()));
    }
  }

  @EventSourcingHandler
  public void on(ReservationRejectedEvent event) {
    this.conferenceId = event.getConferenceId();
    this.remainingSeats = 0;
  }

  @EventSourcingHandler
  public void on(ReservationAcceptedEvent event) {
    this.conferenceId = event.getConferenceId();
    // TODO: add pending reservations
    this.remainingSeats -= event.getNumberOfSeats();
  }

  protected SeatsAvailability() {
  }
}
