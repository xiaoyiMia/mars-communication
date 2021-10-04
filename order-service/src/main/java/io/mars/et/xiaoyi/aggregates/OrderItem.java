package io.mars.et.xiaoyi.aggregates;

import lombok.AllArgsConstructor;
import lombok.Data;
import org.axonframework.modelling.command.EntityId;

@Data
@AllArgsConstructor
public class OrderItem {
  @EntityId
  private String orderItemId;
  private long typeId;
  private int quantity;
}
