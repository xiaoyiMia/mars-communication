package io.mars.et.xiaoyi.commands;

import lombok.AllArgsConstructor;
import lombok.Getter;
import org.axonframework.modelling.command.TargetAggregateIdentifier;

@Getter
@AllArgsConstructor
public class MarkOrderAsBookedCommand {
  @TargetAggregateIdentifier
  private final String orderId;
}
