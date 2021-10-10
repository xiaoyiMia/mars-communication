package io.mars.et.xiaoyi.events;

import io.mars.et.xiaoyi.common.ID;
import lombok.Getter;

import java.util.List;

/**
 * The payload of the concert order placed event.
 */
@Getter
public class ConcertOrderPlacedEvent extends ConcertOrderEvent {
  private List<SeatReservation> seatReservations;

  public ConcertOrderPlacedEvent(ID orderId, ID concertId, List<SeatReservation> seatReservations) {
    super(orderId, concertId);
    this.seatReservations = seatReservations;
  }
}
