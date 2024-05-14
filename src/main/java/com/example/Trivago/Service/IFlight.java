package com.example.Trivago.Service;

import com.example.Trivago.DTO.FlightDTO;


import java.time.LocalDate;
import java.util.List;

public interface IFlight {
    List<FlightDTO> getAll();
    List<FlightDTO> getFlightByDate(LocalDate date_from, LocalDate date_to, String origin, String destination);
}
