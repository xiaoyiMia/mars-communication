package io.mars.et.xiaoyi.events;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class OrderBookedEvent {
  private String orderId;
}
