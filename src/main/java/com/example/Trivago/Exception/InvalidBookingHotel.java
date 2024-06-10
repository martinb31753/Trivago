package com.example.Trivago.Exception;

import lombok.Data;
import lombok.Getter;

@Data
@Getter
public class InvalidBookingHotel extends RuntimeException {
    public InvalidBookingHotel(String message) {
        super(message);
    }
}
