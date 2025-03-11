package com.sp.library.exception;

public class UserRegisteredBeforeException extends RuntimeException {
  public UserRegisteredBeforeException(String message) {
    super(message);
  }
}
