package io.mars.et.xiaoyi.controllers.mappers;

import io.mars.et.xiaoyi.commands.RegisterToConferenceCommand;
import io.mars.et.xiaoyi.controllers.dto.RegistrationOrderRequest;

public interface RegistrationOrderMapper {
  RegisterToConferenceCommand requestToRegisterCommand(RegistrationOrderRequest request);
}
