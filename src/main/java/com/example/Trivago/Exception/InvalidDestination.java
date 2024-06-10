package com.example.Trivago.Exception;

public class InvalidDestination extends RuntimeException{

    public InvalidDestination(String message) {
        super(message);
    }
}
