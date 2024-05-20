package com.example.Trivago.Controller;
import com.example.Trivago.DTO.FlightDTO;
import com.example.Trivago.Model.Flight;
import com.example.Trivago.Service.IFlight;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1")
public class FlightController {

    @Autowired
    IFlight flightService;


    @GetMapping("/flights")
    public ResponseEntity<?> getAll() {
        List<FlightDTO> flights = flightService.getAll();
        return ResponseEntity.ok(flights);
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


}
