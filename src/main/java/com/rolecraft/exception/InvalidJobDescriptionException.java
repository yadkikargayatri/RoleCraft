package com.rolecraft.exception;

public class InvalidJobDescriptionException extends RuntimeException {
    public InvalidJobDescriptionException(String message) {
        super(message);
    }
}
