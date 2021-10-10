package io.mars.et.xiaoyi.common;

public interface ID {

  // Get the a spring represents this ID information. Must be implemented.
  String toString();

  // Compare if the given obj is equals to the current ID. Must be implemented.
  boolean equals(Object obj);

  // The hash code of the ID, must be implemented.
  int hashCode();
}
