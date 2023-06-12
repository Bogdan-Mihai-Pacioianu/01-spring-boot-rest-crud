package com.proj.demo.rest;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;


@ControllerAdvice
public class CarRestExceptionHandler {
    // Add exception handle here
    @ExceptionHandler
    public ResponseEntity<CarErrorResponse> handleException(CarNotFoundException exc) {

        // create a CarErrorResponse
        CarErrorResponse error = new CarErrorResponse();
        error.setStatus(HttpStatus.NOT_FOUND.value());
        error.setMessage(exc.getMessage());
        error.setTimeStamp(System.currentTimeMillis());

        // return ResponseEntity

        return new ResponseEntity<>(error, HttpStatus.NOT_FOUND);
    }
    // add another exception handler ... to catch any exception

    @ExceptionHandler
    public ResponseEntity<CarErrorResponse> handleException(Exception exc){
        // create a Car Error Response
        CarErrorResponse error = new CarErrorResponse();

        error.setStatus(HttpStatus.BAD_REQUEST.value());
        error.setMessage(exc.getMessage());
        error.setTimeStamp(System.currentTimeMillis());

        return new ResponseEntity<>(error, HttpStatus.BAD_REQUEST);

    }

}
