package com.example.Trivago.Repository;

import com.example.Trivago.DTO.Request.FlightReservationRequestDTO;
import com.example.Trivago.DTO.Response.FlightReservationResponseDTO;
import com.example.Trivago.Model.Flight;

import java.util.List;

public interface IFlightRepository {

    List<Flight> getAll();
    Flight getById(String flightCode);

}