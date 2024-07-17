package com.example.Trivago.Service;

import com.example.Trivago.DTO.Request.FlightReservationRequestDTO;
import com.example.Trivago.DTO.Request.FlightReservationRequestDetailDTO;
import com.example.Trivago.DTO.Response.FlightReservationResponseDTO;
import com.example.Trivago.DTO.Response.RespuestaDTO;
import com.example.Trivago.Entity.FlightBooking;

import java.util.List;

public interface IFlightReservationService {
    List<FlightReservationResponseDTO> findAll();

    FlightReservationResponseDTO flightReservation(FlightReservationRequestDTO request);

    RespuestaDTO updateFlightReservation(FlightReservationRequestDTO editReservation, Long id);

    RespuestaDTO cancelFlight(Long id);



}
