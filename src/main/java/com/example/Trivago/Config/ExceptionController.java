package com.example.Trivago.Config;

import com.example.Trivago.DTO.ErrorDTO;
import com.example.Trivago.Exception.InvalidReservation;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestController;

@ControllerAdvice(annotations = RestController.class)
public class ExceptionController {

    @ExceptionHandler(InvalidReservation.class)
    public ResponseEntity<ErrorDTO> handleInvalidReservation(InvalidReservation ex) {
        ErrorDTO errorDTO = new ErrorDTO(ex.getMessage(), 404);
        return new ResponseEntity<>(errorDTO, HttpStatus.NOT_FOUND);
    }

}