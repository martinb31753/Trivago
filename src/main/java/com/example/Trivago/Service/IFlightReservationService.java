package com.example.Trivago.Service;

import com.example.Trivago.DTO.Request.FlightReservationRequestDTO;
import com.example.Trivago.DTO.Request.FlightReservationRequestDetailDTO;
import com.example.Trivago.DTO.Response.FlightReservationResponseDTO;
import com.example.Trivago.DTO.Response.RespuestaDTO;

public interface IFlightReservationService {

    FlightReservationResponseDTO flightReservation(FlightReservationRequestDTO request);

    RespuestaDTO cancelFlight(Long id);
}
