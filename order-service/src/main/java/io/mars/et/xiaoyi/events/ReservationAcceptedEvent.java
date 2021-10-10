package io.mars.et.xiaoyi.events;

import io.mars.et.xiaoyi.common.ID;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class ReservationAcceptedEvent {
  private ID reservationId;
  private ID conferenceId;
  private int numberOfSeats;
}
