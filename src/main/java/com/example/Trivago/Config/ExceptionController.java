package com.example.Trivago.Config;

import com.example.Trivago.Exception.InvalidReservation;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestController;

@ControllerAdvice(annotations = RestController.class)

public class ExceptionController {

    @ExceptionHandler(InvalidReservation.class)
    public ResponseEntity<String> InvalidReservationNotFound(InvalidReservation ex) {
        return new ResponseEntity<>(ex.getMessage(), HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(InvalidReservation.class)
    public ResponseEntity<String> InvalidReservationInternalServerError(InvalidReservation ex) {
        return new ResponseEntity<>(ex.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
    }
}
