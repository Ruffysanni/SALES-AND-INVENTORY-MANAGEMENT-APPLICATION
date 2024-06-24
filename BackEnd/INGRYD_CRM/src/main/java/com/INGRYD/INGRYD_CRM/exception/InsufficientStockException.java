package com.INGRYD.INGRYD_CRM.exception;


public class InsufficientStockException extends RuntimeException{
    public InsufficientStockException() {
    }

    public InsufficientStockException(String message) {
        super(message);
    }

}
