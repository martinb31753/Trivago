package com.example.Trivago.Exception;

import lombok.Data;
import lombok.Getter;

@Data
@Getter
public class HotelNotFound extends RuntimeException {
    public HotelNotFound(String message) {
        super(message);
    }
}
