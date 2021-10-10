package io.mars.et.xiaoyi.commands;

import io.mars.et.xiaoyi.common.ID;
import lombok.Getter;

import java.util.List;

/**
 * The payload of the command to place order for a concert.
 */
@Getter
public class PlaceConcertOrderCommand extends ConcertOrderCommand {
  private final List<SeatReservation> seatReservations;

  public PlaceConcertOrderCommand(ID orderId, ID concertId, List<SeatReservation> seatReservations) {
    super(orderId, concertId);
    this.seatReservations = seatReservations;
  }
}
