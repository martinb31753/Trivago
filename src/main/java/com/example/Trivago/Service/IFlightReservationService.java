package com.example.Trivago.Service;

import com.example.Trivago.DTO.FlightDTO;
import com.example.Trivago.DTO.Request.FlightReservationRequestDTO;
import com.example.Trivago.DTO.Request.FlightReservationRequestDetailDTO;
import com.example.Trivago.DTO.Response.FlightReservationResponseDTO;
import com.example.Trivago.Entity.Flight;
import com.example.Trivago.Entity.FlightBooking;

import java.util.Collection;
import java.util.List;

public interface IFlightReservationService {

    // FlightReservationResponseDTO flightReservation(FlightReservationRequestDTO request);

    FlightReservationResponseDTO flightReservation(FlightReservationRequestDTO request);

    List<FlightBooking> findAll();
}
