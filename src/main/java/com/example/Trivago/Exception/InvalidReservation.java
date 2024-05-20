package com.example.Trivago.Exception;

import lombok.Data;

@Data
public class InvalidReservation extends RuntimeException {
    public InvalidReservation(String message) {
        super(message);
    }
}