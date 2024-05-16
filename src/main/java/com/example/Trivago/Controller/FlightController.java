package com.example.Trivago.Controller;
import com.example.Trivago.DTO.FlightDTO;
import com.example.Trivago.Service.IFlight;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/v1")
public class FlightController {

    @Autowired
    IFlight flightService;


    @GetMapping("/flights")
    public ResponseEntity<?> getAllHotels() {
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
}