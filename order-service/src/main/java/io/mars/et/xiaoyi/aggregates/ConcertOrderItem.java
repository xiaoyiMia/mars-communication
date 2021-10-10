package io.mars.et.xiaoyi.aggregates;

import io.mars.et.xiaoyi.common.ID;
import io.mars.et.xiaoyi.domains.IOrder;
import lombok.Getter;
import org.axonframework.modelling.command.EntityId;

@Getter
public class ConcertOrderItem extends IOrder.AbstractOrderItem {
  @EntityId
  private ID orderItemId;

  public ConcertOrderItem(ID orderItemId, ID spuId, int quantity) {
    super(spuId, quantity);
  }
}
