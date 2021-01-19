package com.interview.tbv.configuration;

/**
 * Create by Jm on 2021-01-18
 */
public class AuthenticationException extends RuntimeException {
    public AuthenticationException(String message, Throwable cause) {
        super(message, cause);
    }
}
