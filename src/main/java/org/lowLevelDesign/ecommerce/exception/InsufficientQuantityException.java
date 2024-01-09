package org.lowLevelDesign.ecommerce.exception;

public class InsufficientQuantityException extends Exception {

  public InsufficientQuantityException(String message) {
    super(message);
  }

  public InsufficientQuantityException(String message, Throwable cause) {
    super(message, cause);
  }
}
