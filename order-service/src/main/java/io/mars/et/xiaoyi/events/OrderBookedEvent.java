package io.mars.et.xiaoyi.events;

import io.mars.et.xiaoyi.common.ID;
import lombok.Getter;

/**
 * The payload of the order booked event.
 */
@Getter
public class OrderBookedEvent extends OrderEvent {
  public OrderBookedEvent(ID orderId) {
    super(orderId);
  }
}