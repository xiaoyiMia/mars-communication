package io.mars.et.xiaoyi.commands;

import io.mars.et.xiaoyi.domains.IConcertOrder;
import io.mars.et.xiaoyi.common.ID;
import lombok.Getter;

/**
 * Concert IConcertOrder command payload.
 */
@Getter
public abstract class ConcertOrderCommand extends OrderCommand implements IConcertOrder {
  private final ID concertId;

  public ConcertOrderCommand(ID orderId, ID concertId) {
    super(orderId);
    this.concertId = concertId;
  }
}
