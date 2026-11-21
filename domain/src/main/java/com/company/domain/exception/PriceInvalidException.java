package com.company.domain.exception;

public class PriceInvalidException extends RuntimeException {
    public PriceInvalidException(String message) {
        super(message);
    }
}
