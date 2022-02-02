package io.mars.et.xiaoyi.commands;

import io.mars.et.xiaoyi.common.ID;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class OrderPaymentDeadline {
  public final static String DEADLINE_NAME = "orderDeadline";
  public final static int EXP_MINS = 15;

  private ID concertId;
  private ID reservationId;
}
