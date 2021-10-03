package io.mars.et.xiaoyi.events;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.List;

@Getter
@AllArgsConstructor
public class OrderPlacedEvent {
  private String orderId;
  private long conferenceId;
  private List<Seat> seats;

  @Getter
  @AllArgsConstructor
  public static class Seat {
    private String orderItemId;
    private long typeId;
    private int quantity;
  }
}
