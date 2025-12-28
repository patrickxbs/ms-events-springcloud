package com.patrick.registration_service.exception;

public class InsufficientEventCapacityException extends RuntimeException {

  public InsufficientEventCapacityException(String message) {
    super(message);
  }
}
