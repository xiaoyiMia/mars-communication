package io.mars.et.xiaoyi.common;

/**
 * Contract for generating universally unique identifiers.
 */
public interface IdGenerator {
  /**
   * Generate a new identifier.
   * @return the generated identifier
   */
  ID generateId();

  ID fromString(String id);
}
