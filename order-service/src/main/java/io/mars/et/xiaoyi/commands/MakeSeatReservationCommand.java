package io.mars.et.xiaoyi.commands;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import org.axonframework.modelling.command.TargetAggregateIdentifier;

@Getter
@AllArgsConstructor
public class MakeSeatReservationCommand {
  @TargetAggregateIdentifier
  private final long conferenceId; //Cannot change
  private final String reservationId;
  private final int numberOfSeats;
}
