package io.mars.et.xiaoyi.commands;

import lombok.AllArgsConstructor;
import lombok.Data;
import org.axonframework.modelling.command.TargetAggregateIdentifier;

@Data
@AllArgsConstructor
public class MakeSeatReservationCommand {
  @TargetAggregateIdentifier
  private final long conferenceId; //Cannot change
  private final String reservationId;
  private final int numberOfSeats;
}
