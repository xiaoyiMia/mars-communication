package io.mars.et.xiaoyi.events;

import io.mars.et.xiaoyi.common.ID;

/**
 * The payload of the order rejected event.
 */
public class OrderRejectedEvent extends OrderEvent {
  public OrderRejectedEvent(ID orderId) {
    super(orderId);
  }
}