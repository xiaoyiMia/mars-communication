package io.mars.et.xiaoyi.events;

import io.mars.et.xiaoyi.domains.IConcertOrder;
import io.mars.et.xiaoyi.common.ID;
import lombok.Getter;

/**
 * The payload of a concert order event.
 */
@Getter
public abstract class ConcertOrderEvent extends OrderEvent implements IConcertOrder {
  private final ID concertId;

  public ConcertOrderEvent(ID orderId, ID concertId) {
    super(orderId);
    this.concertId = concertId;
  }
}
