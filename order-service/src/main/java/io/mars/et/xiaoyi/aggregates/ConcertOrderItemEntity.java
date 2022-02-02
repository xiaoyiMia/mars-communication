package io.mars.et.xiaoyi.aggregates;

import io.mars.et.xiaoyi.common.ID;
import io.mars.et.xiaoyi.domains.IOrder;
import lombok.Getter;
import org.axonframework.modelling.command.EntityId;

@Getter
public class ConcertOrderItemEntity extends IOrder.AbstractOrderItem {
  @EntityId
  private ID orderItemId;

  public ConcertOrderItemEntity(ID orderItemId, ID spuId, int quantity) {
    super(spuId, quantity);
  }
}
