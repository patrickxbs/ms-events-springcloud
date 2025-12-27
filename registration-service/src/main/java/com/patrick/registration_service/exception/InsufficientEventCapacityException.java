package com.patrick.registration_service.exception;

public class InsufficientEventCapacityException extends ApiException {

  public InsufficientEventCapacityException(String message) {
    super(message);
  }
}
