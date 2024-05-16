package com.example.Trivago.Controller;

import com.example.Trivago.DTO.Request.FlightReservationRequestDTO;
import com.example.Trivago.DTO.Request.FlightReservationRequestDetailDTO;
import com.example.Trivago.DTO.Response.FlightReservationResponseDTO;
import com.example.Trivago.Service.IFlight;
import com.example.Trivago.Service.IFlightReservationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1")
public class FlightReservationController {

    @Autowired
    IFlightReservationService flightService;

    @PostMapping("/flight-reservation")
    public ResponseEntity<?> flightReservation(@RequestBody FlightReservationRequestDTO request) {
        FlightReservationResponseDTO response = flightService.flightReservation(request);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }
}
