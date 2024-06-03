package com.example.Trivago.Config;

import com.example.Trivago.DTO.ErrorDTO;
import com.example.Trivago.DTO.ExceptionDTO;
import com.example.Trivago.Exception.*;
import jakarta.validation.ConstraintViolation;
import jakarta.validation.ConstraintViolationException;
import org.springframework.context.support.DefaultMessageSourceResolvable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestController;

import java.util.stream.Collectors;

@ControllerAdvice(annotations = RestController.class)
public class ExceptionController {

    //Manejo de Excepciones

    @ExceptionHandler(InvalidReservationFlight.class)
    public ResponseEntity<ExceptionDTO> handleInvalidReservation(InvalidReservationFlight ex) {
        ExceptionDTO exceptionDTO = new ExceptionDTO(ex.getMessage(), HttpStatus.BAD_REQUEST.value());
        return new ResponseEntity<>(exceptionDTO, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(InvalidBookingHotel.class)
    public ResponseEntity<ExceptionDTO> handleInvalidBooking(InvalidBookingHotel ex) {
        ExceptionDTO exceptionDTO = new ExceptionDTO(ex.getMessage(), HttpStatus.BAD_REQUEST.value());
        return new ResponseEntity<>(exceptionDTO, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(FlightNotFound.class)
    public ResponseEntity<ExceptionDTO> handleFlightNotFound(FlightNotFound ex) {
        ExceptionDTO exceptionDTO = new ExceptionDTO(ex.getMessage(), HttpStatus.NOT_FOUND.value());
        return new ResponseEntity<>(exceptionDTO, HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(HotelNotFound.class)
    public ResponseEntity<ExceptionDTO> handleHotelNotFound(HotelNotFound ex) {
        ExceptionDTO exceptionDTO = new ExceptionDTO(ex.getMessage(), HttpStatus.NOT_FOUND.value());
        return new ResponseEntity<>(exceptionDTO, HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(InvalidDate.class)
    public ResponseEntity<ExceptionDTO> handleInvalidDate(InvalidDate ex) {
        ExceptionDTO exceptionDTO = new ExceptionDTO(ex.getMessage(), HttpStatus.BAD_REQUEST.value());
        return new ResponseEntity<>(exceptionDTO, HttpStatus.BAD_REQUEST);
    }

    //Manejo de Validaciones

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<ErrorDTO> validationException(MethodArgumentNotValidException e){
        return ResponseEntity.badRequest().body(
                new ErrorDTO("Se encontraron los siguientes errores en las validaciones: @Valid del DTO",
                        e.getAllErrors().stream()
                                .map(DefaultMessageSourceResolvable::getDefaultMessage)
                                .collect(Collectors.toList())
                )
        );
    }

    @ExceptionHandler(ConstraintViolationException.class)
    public ResponseEntity<ErrorDTO> validationException(ConstraintViolationException e){
        return ResponseEntity.badRequest().body(
                new ErrorDTO("Se encontraron los siguientes errores en las validaciones en el PathVariable y RequestParam ",
                        e.getConstraintViolations().stream()
                                .map(ConstraintViolation::getMessage)
                                .collect(Collectors.toList())
                )
        );
    }



}