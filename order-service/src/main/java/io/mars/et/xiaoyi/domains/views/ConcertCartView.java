package io.mars.et.xiaoyi.domains.views;

import io.mars.et.xiaoyi.common.ID;
import io.mars.et.xiaoyi.domains.IConcertOrder;
import lombok.*;

import java.time.ZonedDateTime;
import java.util.List;

@Data
public class ConcertCartView implements IConcertOrder {
  private ID cartId;
  private ID concertId;
  private ZonedDateTime createdAt;
  private ZonedDateTime updatedAt;

  private List<OrderItemView> orderItems;

  @Getter
  @Setter
  @EqualsAndHashCode(callSuper = false)
  static class OrderItemView extends IConcertOrder.AbstractOrderItem {
    private ZonedDateTime createdAt;
    private ZonedDateTime updatedAt;

    public OrderItemView(ID spuId, int quantity) {
      super(spuId, quantity);
    }
  }

}
