package com.example.order_service.exceptions;

public class EmptyCartException extends RuntimeException {
    public EmptyCartException(String message) {
        super(message);
    }
}
