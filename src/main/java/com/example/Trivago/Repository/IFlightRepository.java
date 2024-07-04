package com.example.Trivago.Repository;

import com.example.Trivago.Entity.Flight;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface IFlightRepository extends JpaRepository<Flight,Long> {

    List<Flight> getAll();

    Flight getByFlightNumber(String flightCode);

    Flight save(Flight flight);

    boolean update(Flight flight);

    boolean delete(String flightNumber);

    Optional<Flight> getByCode(String flightCode);

//    Flight getById(String flightNumber); tiene la mism lógica que getByFlightNumber
}