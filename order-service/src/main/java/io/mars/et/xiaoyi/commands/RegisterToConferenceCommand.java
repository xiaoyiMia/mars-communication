package io.mars.et.xiaoyi.commands;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import org.axonframework.modelling.command.TargetAggregateIdentifier;

import java.util.List;

@Getter
@AllArgsConstructor
public class RegisterToConferenceCommand {
  @TargetAggregateIdentifier
  private final String orderId;
  private final long conferenceId;
  private final List<Seat> bookedSeats;

  @Getter
  @AllArgsConstructor
  public static class Seat {
    private final long typeId;
    private final int quantity;
  }
}
