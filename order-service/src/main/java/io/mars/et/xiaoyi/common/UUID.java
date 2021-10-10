package io.mars.et.xiaoyi.common;

import lombok.EqualsAndHashCode;

@EqualsAndHashCode(doNotUseGetters=true)
class UUID implements ID {
  private String id;

  static UUID generate() {
    return new UUID();
  }

  static UUID fromString(String id) {
    return new UUID(id);
  }

  private UUID() {
    this.id = java.util.UUID.randomUUID().toString();
  }

  private UUID(String id) {
    this.id = id;
  }

  @Override
  public String toString() {
    return id;
  }
}
