package com.example.Trivago.Service;

import com.example.Trivago.DTO.FlightDTO;
import com.example.Trivago.DTO.Request.FlightReservationRequestDetailDTO;
import com.example.Trivago.DTO.Response.FlightReservationResponseDetailDTO;


import java.util.List;

public interface IFlight {
    List<FlightDTO> getAll();

    FlightDTO getById(String flightCode);
}
