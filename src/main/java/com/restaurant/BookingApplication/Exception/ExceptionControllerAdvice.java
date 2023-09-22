package com.restaurant.BookingApplication.Exception;

import com.restaurant.BookingApplication.Controller.Status;
import com.restaurant.BookingApplication.Dto.ResponseDto;
import jdk.jfr.RecordingState;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.client.HttpClientErrorException;

@ControllerAdvice
public class ExceptionControllerAdvice {

    @ExceptionHandler(value = CustomException.class)
    public final ResponseEntity<ResponseDto> handleCustomException(CustomException exception) {

        return new ResponseEntity<>(new ResponseDto(Status.FAILURE, exception.getMessage()), HttpStatus.BAD_REQUEST);

    }

    @ExceptionHandler(value = AuthenticationFailException.class)
    public final ResponseEntity<ResponseDto> handleAuthenticationFailException(AuthenticationFailException exception) {

        return new ResponseEntity<>(new ResponseDto(Status.FAILURE, exception.getMessage()), HttpStatus.BAD_REQUEST);

    }


}
