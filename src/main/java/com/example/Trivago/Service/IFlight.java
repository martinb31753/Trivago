package com.example.Trivago.Service;

import com.example.Trivago.DTO.FlightDTO;
import com.example.Trivago.DTO.Response.RespuestaDTO;


import java.time.LocalDate;
import java.util.List;

public interface IFlight {

    List<FlightDTO> getAll();
    List<FlightDTO> getFlightByDate(LocalDate date_from, LocalDate date_to, String origin, String destination);

    FlightDTO getByFlightNumber(String flightCode);

    RespuestaDTO addNewFlight(FlightDTO flightDTO);

    RespuestaDTO updateFlight(FlightDTO updateFlight);

    RespuestaDTO deleteFlightById(String flightNumber);
}
