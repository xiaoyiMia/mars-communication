package io.mars.et.xiaoyi.commands;

import io.mars.et.xiaoyi.common.ID;
import lombok.Getter;

/**
 * payload of the Command to change the order's status to booked.
 */
@Getter
public class MarkOrderAsBookedCommand extends OrderCommand {
  public MarkOrderAsBookedCommand(ID orderId) {
    super(orderId);
  }
}
