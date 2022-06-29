package io.mars.et.xiaoyi.controllers;

import io.mars.et.xiaoyi.controllers.dto.ConcertOrderRequest;
import io.mars.et.xiaoyi.controllers.mappers.RegistrationOrderMapper;
import org.axonframework.commandhandling.gateway.CommandGateway;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.lang.NonNull;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class RegistrationController {

  private final CommandGateway commandGateway;
  private final RegistrationOrderMapper mapper;

  @Autowired
  public RegistrationController(CommandGateway commandGateway, RegistrationOrderMapper mapper) {
    this.commandGateway = commandGateway;
    this.mapper = mapper;
  }

  @PostMapping("/concerts/{concertId}/cart")
  public void createCart(
      @PathVariable("concertId") String concertId
  ) {

  }


  @PostMapping("/concerts/{concertId}/order")
  public void registerToConference(
      @RequestBody @NonNull ConcertOrderRequest request,
      @PathVariable("concertId") String concertId
  ) {
    commandGateway.send(mapper.requestToRegisterCommand(concertId, request));
  }
}
