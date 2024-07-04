package com.example.Trivago.Repository;

import com.example.Trivago.Entity.Flight;

import java.util.List;

public interface IFlightRepository {

    List<Flight> getAll();

    Flight getByFlightNumber(String flightCode);

    boolean save(Flight flight);

    boolean update(Flight flight);

    boolean delete(String flightNumber);

//    Flight getById(String flightNumber); tiene la mism lógica que getByFlightNumber
}