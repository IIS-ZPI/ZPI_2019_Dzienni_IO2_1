package com.zpi.currencyapp;

public class InvalidDateException extends Exception {

    private static final long serialVersionUID = 1L;

    public InvalidDateException(Exception e) {
        super(e);
    }

}
