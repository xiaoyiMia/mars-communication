package io.mars.et.xiaoyi.commands;

import io.mars.et.xiaoyi.common.ID;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import org.axonframework.modelling.command.TargetAggregateIdentifier;

/**
 * The payload of command to make seat reservation
 */
@Getter
@AllArgsConstructor
public class MakeSeatReservationCommand {
  @TargetAggregateIdentifier
  private final ID conferenceId; //Cannot change
  private final ID reservationId;
  private final int numberOfSeats;
}
