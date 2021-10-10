package io.mars.et.xiaoyi.commands;

import io.mars.et.xiaoyi.common.ID;
import io.mars.et.xiaoyi.domains.IOrder;
import lombok.AllArgsConstructor;
import lombok.Getter;
import org.axonframework.modelling.command.TargetAggregateIdentifier;

/**
 * The payload of the command to an IConcertOrder.
 */
@Getter
@AllArgsConstructor
public abstract class OrderCommand implements IOrder {
  private final ID orderId;

  @TargetAggregateIdentifier
  public ID getOrderId() {
    return orderId;
  }
}
