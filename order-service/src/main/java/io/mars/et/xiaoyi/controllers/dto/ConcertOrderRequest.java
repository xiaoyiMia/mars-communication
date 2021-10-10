package io.mars.et.xiaoyi.controllers.dto;

import io.mars.et.xiaoyi.common.ID;
import io.mars.et.xiaoyi.domains.IConcertOrder;
import lombok.Data;

import java.util.List;

@Data
public class ConcertOrderRequest implements IConcertOrder {
  private List<OrderItem> items;

  @Data
  public static class OrderItem {
    private ID spuId;
    private int quantity;
  }
}
