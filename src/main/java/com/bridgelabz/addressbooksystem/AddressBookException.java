package com.bridgelabz.addressbooksystem;

public class AddressBookException extends RuntimeException {
	enum ExceptionType {
        EMPTY_STRING,
        NULL_STRING,
        INCORRECT_QUERY,
        UPDATE_FAILED,
        CONNECTION_FAILED;
    }

    ExceptionType exceptionType;

    public AddressBookException(ExceptionType exceptionType, String message) {
        super(message);
        this.exceptionType = exceptionType;
    }
}