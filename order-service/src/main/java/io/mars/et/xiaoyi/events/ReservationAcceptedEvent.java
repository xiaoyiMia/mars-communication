package io.mars.et.xiaoyi.events;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class ReservationAcceptedEvent {
  private String reservationId;
  private long conferenceId;
}
