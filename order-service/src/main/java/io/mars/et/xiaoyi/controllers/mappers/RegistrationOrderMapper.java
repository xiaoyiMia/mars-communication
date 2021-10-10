package io.mars.et.xiaoyi.controllers.mappers;

import io.mars.et.xiaoyi.commands.PlaceConcertOrderCommand;
import io.mars.et.xiaoyi.controllers.dto.ConcertOrderRequest;

public interface RegistrationOrderMapper {
  PlaceConcertOrderCommand requestToRegisterCommand(String concertId, ConcertOrderRequest request);
}
