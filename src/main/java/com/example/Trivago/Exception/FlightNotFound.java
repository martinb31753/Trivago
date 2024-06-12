package com.example.Trivago.Exception;

import lombok.Data;
import lombok.Getter;

@Data
@Getter
public class FlightNotFound extends RuntimeException{
    public FlightNotFound(String message) {
        super(message);
    }
}
