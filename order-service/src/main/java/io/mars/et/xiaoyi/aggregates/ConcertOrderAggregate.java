package io.mars.et.xiaoyi.aggregates;

import io.mars.et.xiaoyi.commands.MarkOrderAsBookedCommand;
import io.mars.et.xiaoyi.commands.PlaceConcertOrderCommand;
import io.mars.et.xiaoyi.commands.RejectOrderCommand;
import io.mars.et.xiaoyi.common.ID;
import io.mars.et.xiaoyi.domains.IConcertOrder;
import io.mars.et.xiaoyi.events.OrderBookedEvent;
import io.mars.et.xiaoyi.events.ConcertOrderPlacedEvent;
import io.mars.et.xiaoyi.events.OrderRejectedEvent;
import io.mars.et.xiaoyi.exceptions.InvalidOperationException;
import lombok.Data;
import org.axonframework.commandhandling.CommandHandler;
import org.axonframework.eventsourcing.EventSourcingHandler;
import org.axonframework.modelling.command.AggregateIdentifier;
import org.axonframework.modelling.command.AggregateMember;
import org.axonframework.spring.stereotype.Aggregate;

import java.util.ArrayList;
import java.util.List;

import static org.axonframework.modelling.command.AggregateLifecycle.apply;

@Aggregate
@Data
public class ConcertOrderAggregate implements IConcertOrder {
  @AggregateIdentifier
  private ID orderId; // Cannot be updated
  private ID concertId;
  private State state;
  @AggregateMember
  private List<ConcertOrderItemEntity> seatReservations = new ArrayList<>();

  @CommandHandler
  public ConcertOrderAggregate(PlaceConcertOrderCommand orderCommand) {
    // Raise an event
    apply(new ConcertOrderPlacedEvent(
        orderCommand.getOrderId(),
        orderCommand.getConcertId(),
        orderCommand.getSeatReservations()
    ));
  }

  @EventSourcingHandler
  public void on(ConcertOrderPlacedEvent event) {
    this.orderId = event.getOrderId();
    this.concertId = event.getConcertId();
    this.state = State.CREATED;
    event.getSeatReservations().forEach(seat -> seatReservations.add(
        new ConcertOrderItemEntity(seat.getOrderItemId(), seat.getSeatTypeId(), seat.getQuantity())
    ));
  }

  @CommandHandler
  public void handle(MarkOrderAsBookedCommand command) {
    if (this.state != State.CREATED) {
      throw new InvalidOperationException();
    }
    apply(new OrderBookedEvent(command.getOrderId()));
  }

  @EventSourcingHandler
  public void on(OrderBookedEvent event) {
    this.state = State.BOOKED;
  }

  @CommandHandler
  public void handle(RejectOrderCommand command) {
    if (this.state == State.CONFIRMED) {
      throw new InvalidOperationException();
    }
    apply(new OrderRejectedEvent(command.getOrderId()));
  }

  @EventSourcingHandler
  public void on(OrderRejectedEvent event) {
    this.state = State.REJECTED;
  }

  protected ConcertOrderAggregate() {
  }

  private enum State {
    CREATED, BOOKED, REJECTED, CONFIRMED
  }
}
