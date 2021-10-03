package io.mars.et.xiaoyi.commands;

import lombok.AllArgsConstructor;
import lombok.Data;
import org.axonframework.modelling.command.TargetAggregateIdentifier;

import java.util.List;

@Data
public class RegisterToConferenceCommand {
  @TargetAggregateIdentifier
  private final String orderId;
  private final long conferenceId;
  private final List<Seat> bookedSeats;

  @Data
  @AllArgsConstructor
  public static class Seat {
    private final long typeId;
    private final int quantity;
  }
}
