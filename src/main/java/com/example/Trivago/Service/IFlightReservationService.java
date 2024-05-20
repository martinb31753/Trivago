package com.example.Trivago.Service;

import com.example.Trivago.DTO.Request.FlightReservationRequestDTO;
import com.example.Trivago.DTO.Request.FlightReservationRequestDetailDTO;
import com.example.Trivago.DTO.Response.FlightReservationResponseDTO;

public interface IFlightReservationService {

    FlightReservationResponseDTO flightReservation(FlightReservationRequestDTO request);
}
