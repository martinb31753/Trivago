package com.example.Trivago.Exception;

import lombok.Data;
import lombok.Getter;

@Data
@Getter
public class InvalidDate extends RuntimeException {
    public InvalidDate(String message) {
        super(message);
    }

}
