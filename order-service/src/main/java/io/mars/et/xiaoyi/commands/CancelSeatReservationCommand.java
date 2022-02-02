package io.mars.et.xiaoyi.commands;

import io.mars.et.xiaoyi.common.ID;
import lombok.AllArgsConstructor;
import lombok.Getter;
import org.axonframework.modelling.command.TargetAggregateIdentifier;

/**
 * The payload of command to cancel seat reservation
 */
@Getter
@AllArgsConstructor
public class CancelSeatReservationCommand {
  @TargetAggregateIdentifier
  private final ID conferenceId;
  private final ID reservationId;
}
