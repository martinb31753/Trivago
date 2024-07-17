package com.example.Trivago.Controller;

import com.example.Trivago.DTO.Request.FlightReservationRequestDTO;
import com.example.Trivago.DTO.Response.FlightReservationResponseDTO;
import com.example.Trivago.Service.IFlightReservationService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/flight-reservation")
@Validated
public class FlightReservationController {

    @Autowired
    IFlightReservationService flightService;

    @GetMapping()
    public ResponseEntity<List<FlightReservationResponseDTO>> getFlights() {
        List<FlightReservationResponseDTO> flightsReservations = flightService.findAll();
        return ResponseEntity.ok(flightsReservations);
    }

    @PostMapping("/new")
    public ResponseEntity<?> flightReservation(@RequestBody @Valid FlightReservationRequestDTO request) {
        FlightReservationResponseDTO response = flightService.flightReservation(request);
        return new ResponseEntity<>(response, HttpStatus.CREATED);
    }

    @PutMapping("/edit")
    public ResponseEntity<?> editFlightReservation(@RequestBody @Valid FlightReservationRequestDTO editReservation,
                                                   @RequestParam Long id) {
        return new ResponseEntity<>(flightService.updateFlightReservation(editReservation, id), HttpStatus.OK);
    }

    @PutMapping("/delete")
    public ResponseEntity<?> deleteFlightReservation(@RequestParam("id") Long id) {
        flightService.cancelFlight(id);
        return ResponseEntity.ok("Vuelo No. " + id + " eliminado con exito");
    }
}