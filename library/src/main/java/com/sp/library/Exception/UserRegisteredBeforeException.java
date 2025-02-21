package com.sp.library.Exception;

public class UserRegisteredBeforeException extends RuntimeException {
    public UserRegisteredBeforeException(String message) {
        super(message);
    }
}
