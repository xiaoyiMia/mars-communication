package io.mars.et.xiaoyi.domains;

import io.mars.et.xiaoyi.common.ID;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;

public interface IOrder {

  @AllArgsConstructor
  abstract class AbstractOrderItem {
    @Getter private ID orderItemId;
    @Getter(AccessLevel.PROTECTED)private ID spuId;
    @Getter(AccessLevel.PROTECTED)private ID skuId;
    @Getter private int quantity;

    public AbstractOrderItem(ID orderItemId, ID spuId, int quantity) {
      this(orderItemId, spuId, null, quantity);
    }

    public AbstractOrderItem(ID spuId, int quantity) {
      this(null, spuId, null, quantity);
    }
  }
}
