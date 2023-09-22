package com.restaurant.BookingApplication.Dto;

import com.restaurant.BookingApplication.Controller.Status;

public class ResponseDto {

    private Status status;
    private String message;

    public ResponseDto(Status status, String message) {
        this.status = status;
        this.message = message;
    }

    public ResponseDto() {
    }

    public Status getStatus() {
        return status;
    }

    public void setStatus(Status status) {
        this.status = status;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    @Override
    public String toString() {
        return "ResponseDto{" +
                "status='" + status + '\'' +
                ", message='" + message + '\'' +
                '}';
    }

}
