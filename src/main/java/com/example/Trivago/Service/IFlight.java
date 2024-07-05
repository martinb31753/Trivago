package com.example.Trivago.Service;

import com.example.Trivago.DTO.FlightDTO;
import com.example.Trivago.DTO.Response.RespuestaDTO;


import java.time.LocalDate;
import java.util.List;

public interface IFlight {
    RespuestaDTO deleteFlightByCode(String flightNumber);

    List<FlightDTO> getFlightByDate(LocalDate dateFrom, LocalDate dateTo, String origin, String destination);

    List<FlightDTO> getAll();

    RespuestaDTO addNewFlight(FlightDTO flightDTO);

    RespuestaDTO updateFlight(FlightDTO updateFlight);


}
