package io.mars.et.xiaoyi.commands;

import io.mars.et.xiaoyi.common.ID;
import lombok.Getter;

/**
 * The payload of the command to reject the order.
 */
@Getter
public class RejectOrderCommand extends OrderCommand {
  public RejectOrderCommand(ID orderId) {
    super(orderId);
  }
}
