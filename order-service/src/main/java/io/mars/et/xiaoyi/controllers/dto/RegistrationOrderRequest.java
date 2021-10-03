package io.mars.et.xiaoyi.controllers.dto;

import lombok.Data;

@Data
public class RegistrationOrderRequest extends RegistrationOrder<RegistrationOrderItemRequest> {
  private String orderId;
}
