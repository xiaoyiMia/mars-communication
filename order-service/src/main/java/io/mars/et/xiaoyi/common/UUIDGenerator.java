package io.mars.et.xiaoyi.common;

/**
 * Generate ID using UUID.
 */
public class UUIDGenerator implements IdGenerator {
  @Override
  public ID generateId() {
    return UUID.generate();
  }

  @Override
  public ID fromString(String id) {
    return UUID.fromString(id);
  }
}
