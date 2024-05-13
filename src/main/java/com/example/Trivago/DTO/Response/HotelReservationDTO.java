package com.example.Trivago.DTO.Response;

import com.example.Trivago.Model.Hotel;
import com.example.Trivago.Model.PaymentMethod;
import com.example.Trivago.Model.Person;

import java.util.List;

public class HotelReservationDTO {
    List<Person> person;
    Hotel hotel;
    Integer people_amount;
    Double amount;
    PaymentMethod payment_method;
}