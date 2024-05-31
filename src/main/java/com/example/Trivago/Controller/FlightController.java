package com.example.Trivago.Controller;
import com.example.Trivago.DTO.FlightDTO;
import com.example.Trivago.Model.Flight;
import com.example.Trivago.Service.IFlight;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.Date;
import java.util.List;

@RestController
@RequestMapping("/api/v1")
public class FlightController {

    @Autowired
    IFlight flightService;


    @GetMapping("/flights")
    public ResponseEntity<?> getAllHotels() {
        return new ResponseEntity<>(flightService.getAll(), HttpStatus.OK);
    }

    @PostMapping("/add-new-flight")
    public ResponseEntity<?> addNewFlight(@RequestBody FlightDTO newFlight) {
        return new ResponseEntity<>(flightService.addNewFlight(newFlight), HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> updateFlightById(@PathVariable Long id, @RequestBody FlightDTO updateFlight) {
        return new ResponseEntity<>(flightService.updateFlightById(id, updateFlight), HttpStatus.OK);

    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteFlightById(@PathVariable Long id) {
        return new ResponseEntity<>(flightService.deleteFlightById(id), HttpStatus.OK);
    }

    @GetMapping("/flights?date_from={date_from}&date_to={date_to}&origin={origin}&destination={destination}")
    public ResponseEntity<?> getFlights(
            @PathVariable(value = "date_from", required = false) @DateTimeFormat(pattern = "dd-MM-yyyy") LocalDate date_from,
            @PathVariable(value = "date_to", required = false) @DateTimeFormat(pattern = "dd-MM-yyyy") LocalDate date_to,
            @PathVariable(value = "origin", required = false) String origin,
            @PathVariable(value = "destination", required = false) String destination) {
        List<Flight> flights = flightService.getFlightByDate(date_from, date_to, origin, destination);
        return new ResponseEntity<>(flights, HttpStatus.OK);
    }
}