package io.mars.et.xiaoyi.commands;

import lombok.AllArgsConstructor;
import lombok.Data;
import org.axonframework.modelling.command.TargetAggregateIdentifier;

@Data
@AllArgsConstructor
public class MarkOrderAsBookedCommand {
  @TargetAggregateIdentifier
  private final String orderId;
}
