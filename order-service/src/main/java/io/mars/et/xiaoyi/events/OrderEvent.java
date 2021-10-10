package io.mars.et.xiaoyi.events;

import io.mars.et.xiaoyi.common.ID;
import io.mars.et.xiaoyi.domains.IOrder;
import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * The payload of an order event.
 */
@Getter
@AllArgsConstructor
public abstract class OrderEvent implements IOrder {
  private final ID orderId;
}
