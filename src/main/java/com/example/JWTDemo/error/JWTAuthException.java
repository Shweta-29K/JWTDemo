package com.example.JWTDemo.error;

public class JWTAuthException extends RuntimeException{
    public JWTAuthException(String message) {
        super(message);
    }

    public JWTAuthException(Throwable cause) {
        super(cause);
    }
}
