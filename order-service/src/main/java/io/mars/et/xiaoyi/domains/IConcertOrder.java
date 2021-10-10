package io.mars.et.xiaoyi.domains;

import io.mars.et.xiaoyi.common.ID;
import lombok.Getter;

public interface IConcertOrder extends IOrder {

  @Getter
  class SeatReservation extends AbstractOrderItem {
    public SeatReservation(ID orderItemId, ID seatTypeId, int quantity) {
      super(orderItemId, seatTypeId, quantity);
    }

    public SeatReservation(ID seatTypeId, int quantity) {
      super(seatTypeId, quantity);
    }

    public ID getSeatTypeId() {
      return super.getSpuId();
    }
  }
}
