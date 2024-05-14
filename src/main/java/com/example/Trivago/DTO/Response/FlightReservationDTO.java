package com.example.Trivago.DTO.Response;

import com.example.Trivago.Model.Flight;
import com.example.Trivago.Model.PaymentMethod;
import com.example.Trivago.Model.Person;

import java.util.List;

public class FlightReservationDTO {
    List<Person> person;
    Flight flight;
    PaymentMethod payment_method;
    Integer seats_amount;
    Double amount;
}
