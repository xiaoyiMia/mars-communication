package io.mars.et.xiaoyi.controllers.mappers;

import io.mars.et.xiaoyi.commands.PlaceConcertOrderCommand;
import io.mars.et.xiaoyi.common.IdGenerator;
import io.mars.et.xiaoyi.controllers.dto.ConcertOrderRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.stream.Collectors;

@Component
public class RegistrationOrderMapperImpl implements RegistrationOrderMapper {

  private IdGenerator idGenerator;

  public RegistrationOrderMapperImpl(@Autowired IdGenerator idGenerator) {
    this.idGenerator = idGenerator;
  }

  @Override
  public PlaceConcertOrderCommand requestToRegisterCommand(String concertId, ConcertOrderRequest request) {
    return new PlaceConcertOrderCommand(
        idGenerator.generateId(),
        idGenerator.fromString(concertId),
        request.getItems().stream().map(this::requestItemToCommandItem).collect(Collectors.toList())
    );
  }

  private PlaceConcertOrderCommand.SeatReservation requestItemToCommandItem(ConcertOrderRequest.OrderItem itemRequest) {
    return new PlaceConcertOrderCommand.SeatReservation(itemRequest.getSpuId(), itemRequest.getQuantity());
  }
}
