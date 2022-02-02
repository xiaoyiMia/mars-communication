package io.mars.et.xiaoyi.aggregates;

import io.mars.et.xiaoyi.commands.CancelSeatReservationCommand;
import io.mars.et.xiaoyi.commands.CommitSeatReservationCommand;
import io.mars.et.xiaoyi.commands.MakeSeatReservationCommand;
import io.mars.et.xiaoyi.common.ID;
import io.mars.et.xiaoyi.events.ReservationAcceptedEvent;
import io.mars.et.xiaoyi.events.ReservationRejectedEvent;
import io.mars.et.xiaoyi.exceptions.InvalidOperationException;
import lombok.Data;
import org.axonframework.commandhandling.CommandHandler;
import org.axonframework.eventsourcing.EventSourcingHandler;
import org.axonframework.modelling.command.AggregateIdentifier;
import org.axonframework.modelling.command.AggregateMember;
import org.axonframework.spring.stereotype.Aggregate;

import java.util.HashMap;
import java.util.Map;

import static org.axonframework.modelling.command.AggregateLifecycle.apply;

@Aggregate
@Data
public class SeatsAvailabilityAggregate {
  @AggregateIdentifier
  private ID conferenceId;
  private int remainingSeats = 10;
  @AggregateMember(type = SeatReservationEntity.class)
  private Map<ID, SeatReservationEntity> pendingReservations = new HashMap<>();

  @CommandHandler
  public SeatsAvailabilityAggregate(MakeSeatReservationCommand command) {
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
    this.remainingSeats -= event.getNumberOfSeats();
//    this.pendingReservations.add(new SeatReservationEntity(event.getReservationId(), event.getNumberOfSeats()));
    this.pendingReservations.put(
        event.getReservationId(),
        new SeatReservationEntity(event.getReservationId(), event.getNumberOfSeats())
    );
  }

  @CommandHandler
  public void on(CancelSeatReservationCommand command) {
//    Optional<SeatReservationEntity> reservation = pendingReservations.stream().filter(
//        entity -> entity.getReservationId().equals(command.getReservationId())
//    ).findFirst();
//    if (reservation.isPresent()) {
//      this.remainingSeats += reservation.get().getNumOfSeats();
//      pendingReservations.remove(reservation);
    if (this.pendingReservations.containsKey(command.getReservationId())) {
      this.remainingSeats += this.pendingReservations.get(command.getReservationId()).getNumOfSeats();
      this.pendingReservations.remove(command.getReservationId());
    } else {
      throw new InvalidOperationException();
    }
  }

  @CommandHandler
  public void on(CommitSeatReservationCommand command) {
//    boolean removed = pendingReservations.removeIf(
//        reservation -> reservation.getReservationId().equals(command.getReservationId())
//    );
//
//    if (!removed) {
    SeatReservationEntity reservation = this.pendingReservations.remove(command.getReservationId());

    if (reservation == null) {
      throw new InvalidOperationException();
    }
  }

  protected SeatsAvailabilityAggregate() {
  }
}
