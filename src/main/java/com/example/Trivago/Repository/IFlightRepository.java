package com.example.Trivago.Repository;

import com.example.Trivago.Entity.Flight;

import com.example.Trivago.Entity.FlightBooking;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface IFlightRepository extends JpaRepository<Flight, Long> {
    List<Flight> findAll();

    Flight getByFlightNumber(String flightCode);

    //Flight save(Flight flight);

    //boolean update(Flight flight);

    //boolean delete(String flightNumber);

    //Flight getById(String flightNumber); tiene la mism lógica que getByFlightNumber
}