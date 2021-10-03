package io.mars.et.xiaoyi.controllers.mappers;

import io.mars.et.xiaoyi.commands.RegisterToConferenceCommand;
import io.mars.et.xiaoyi.controllers.dto.RegistrationOrderItemRequest;
import io.mars.et.xiaoyi.controllers.dto.RegistrationOrderRequest;
import org.springframework.stereotype.Component;

import java.util.stream.Collectors;

@Component
public class RegistrationOrderMapperImpl implements RegistrationOrderMapper {
  @Override
  public RegisterToConferenceCommand requestToRegisterCommand(RegistrationOrderRequest request) {
    return new RegisterToConferenceCommand(
        request.getOrderId(),
        request.getConferenceId(),
        request.getItems().stream().map(this::requestItemToCommandItem).collect(Collectors.toList())
    );
  }

  private RegisterToConferenceCommand.Seat requestItemToCommandItem(RegistrationOrderItemRequest itemRequest) {
    return new RegisterToConferenceCommand.Seat(itemRequest.getTypeId(), itemRequest.getQuantity());
  }
}
