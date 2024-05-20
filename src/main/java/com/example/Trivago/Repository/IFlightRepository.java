package com.example.Trivago.Repository;

import com.example.Trivago.Model.Flight;

import java.time.LocalDate;
import java.util.List;

public interface IFlightRepository {

    List<Flight> getAll();

    Flight getById(String id);
}