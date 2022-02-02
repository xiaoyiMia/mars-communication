package io.mars.et.xiaoyi.aggregates;

import io.mars.et.xiaoyi.common.ID;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import org.axonframework.modelling.command.EntityId;

@Getter
@AllArgsConstructor
@EqualsAndHashCode(of = {"reservationId"})
public class SeatReservationEntity {
  @EntityId
  private ID reservationId;
  private int numOfSeats;
}
