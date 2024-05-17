package com.example.Trivago.Controller;
import com.example.Trivago.DTO.FlightDTO;
import com.example.Trivago.Model.Flight;
import com.example.Trivago.Service.IFlight;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDate;
import java.util.Date;
import java.util.List;

@RestController
@RequestMapping("/api/v1")
public class FlightController {

    @Autowired
    IFlight flightService;


    @GetMapping("/flights")
    public ResponseEntity<?> getAllFlights() {
        try {
            List<FlightDTO> flights = flightService.getAll();

            if (flights.isEmpty()) {
                return new ResponseEntity<>("No existen vuelos", HttpStatus.NO_CONTENT);
            } else {
                return new ResponseEntity<>(flights, HttpStatus.OK);
            }
        } catch (Exception e) {
            return new ResponseEntity<>("Error inesperado en vuelos", HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/flightsByDate")
    public ResponseEntity<?> getFlights(
            @RequestParam(value = "date_from", required = false) @DateTimeFormat(pattern = "dd-MM-yyyy") LocalDate date_from,
            @RequestParam(value = "date_to", required = false) @DateTimeFormat(pattern = "dd-MM-yyyy") LocalDate date_to,
            @RequestParam(value = "origin", required = false) String origin,
            @RequestParam(value = "destination", required = false) String destination) {
        try {
            List<Flight> flights = flightService.getFlightByDate(date_from, date_to, origin, destination);
            return new ResponseEntity<>(flights, HttpStatus.OK);
        }
        catch (Exception e) {
            return new ResponseEntity<>("Error inesperado en vuelos", HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}