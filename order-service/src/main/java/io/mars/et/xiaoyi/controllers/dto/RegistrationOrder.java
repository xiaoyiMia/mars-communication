package io.mars.et.xiaoyi.controllers.dto;

import lombok.Data;

import java.util.List;

@Data
abstract class RegistrationOrder<T extends RegistrationOrderItem> {
  private long conferenceId;
  private List<T> items;
}
