package com.example.Trivago.Exception;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Data
@Getter
public class InvalidReservation extends RuntimeException {
    public InvalidReservation(String message) {
        super(message);
    }
}