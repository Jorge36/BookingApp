package com.restaurant.BookingApplication.Exception;

public class CustomException extends IllegalArgumentException {
    public CustomException(String message) {
        super(message);
    }
}
