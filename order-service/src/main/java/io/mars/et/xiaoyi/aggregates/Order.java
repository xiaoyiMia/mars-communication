package io.mars.et.xiaoyi.aggregates;

import io.mars.et.xiaoyi.commands.MarkOrderAsBookedCommand;
import io.mars.et.xiaoyi.commands.RegisterToConferenceCommand;
import io.mars.et.xiaoyi.commands.RejectOrderCommand;
import io.mars.et.xiaoyi.events.OrderBookedEvent;
import io.mars.et.xiaoyi.events.OrderPlacedEvent;
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
import java.util.UUID;
import java.util.stream.Collectors;

import static org.axonframework.modelling.command.AggregateLifecycle.apply;

@Aggregate
@Data
public class Order {
  @AggregateIdentifier
  private String orderId; // Cannot be updated
  private State state;

  @AggregateMember
  private List<OrderItem> orderItems = new ArrayList<>();

  @CommandHandler
  public Order(RegisterToConferenceCommand orderCommand) {
    // Raise an event
    apply(new OrderPlacedEvent(
        orderCommand.getOrderId(),
        orderCommand.getConferenceId(),
        orderCommand.getBookedSeats().stream()
            .map(seat -> new OrderPlacedEvent.Seat(UUID.randomUUID().toString(), seat.getTypeId(), seat.getQuantity()))
            .collect(Collectors.toList())
    ));
  }

  @EventSourcingHandler
  public void on(OrderPlacedEvent event) {
    this.orderId = event.getOrderId();
    this.state = State.CREATED;
    event.getSeats().forEach(seat -> orderItems.add(
        new OrderItem(seat.getOrderItemId(), seat.getTypeId(), seat.getQuantity())
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
    if (this.state != State.CREATED) {
      throw new InvalidOperationException();
    }
    apply(new OrderBookedEvent(command.getOrderId()));
  }

  @EventSourcingHandler
  public void on(OrderRejectedEvent event) {
    this.state = State.REJECTED;
  }

  protected Order() {
  }

  private enum State {
    CREATED, BOOKED, REJECTED, CONFIRMED
  }
}
