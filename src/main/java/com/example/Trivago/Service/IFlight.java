package com.example.Trivago.Service;

import com.example.Trivago.DTO.FlightDTO;
import com.example.Trivago.DTO.Request.FlightReservationRequestDetailDTO;
import com.example.Trivago.DTO.Response.FlightReservationResponseDetailDTO;
import com.example.Trivago.Model.Flight;


import java.util.List;

public interface IFlight {
    List<FlightDTO> getAll();

    FlightDTO getByFlightNumber(String flightCode);

    Flight addNewFlight(FlightDTO flight);

    Flight updateFlightById(Long id, FlightDTO updateFlight);

    Flight deleteFlightById(Long id);
}
