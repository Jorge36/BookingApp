package com.restaurant.BookingApplication.Exception;

public class AuthenticationFailException extends IllegalArgumentException {

    public AuthenticationFailException(String msg) {
        super(msg);
    }

}
