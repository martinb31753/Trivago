package com.example.Trivago.Exception;

import lombok.Data;
import lombok.Getter;

@Data
@Getter
public class InvalidReservationFlight extends RuntimeException {
    public InvalidReservationFlight(String message) {
        super(message);
    }
}