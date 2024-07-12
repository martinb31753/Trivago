package com.example.Trivago.Controller;
import com.example.Trivago.DTO.FlightDTO;
import com.example.Trivago.Service.IFlight;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;


import java.time.LocalDate;
import java.util.List;

@RestController
@RequestMapping("/api/v1/flights")
@Validated
public class FlightController {

    @Autowired
    IFlight flightService;

    @GetMapping()
    public ResponseEntity<?> getAvailableFlights(
            @RequestParam(value = "date_from", required = false) @DateTimeFormat(pattern = "dd-MM-yyyy") LocalDate date_from,
            @RequestParam(value = "date_to", required = false) @DateTimeFormat(pattern = "dd-MM-yyyy") LocalDate date_to,
            @RequestParam (value = "origin", required = false) String origin,
            @RequestParam (value = "destination", required = false) String destination){
        List<FlightDTO> filteredFlights = flightService.getFlightByDate(date_from, date_to, origin, destination);
        return ResponseEntity.ok(filteredFlights);
    }

    @PostMapping("/add-new-flight")
    public ResponseEntity<?> addNewFlight(@RequestBody @Valid FlightDTO newFlight) {
        return new ResponseEntity<>(flightService.addNewFlight(newFlight), HttpStatus.CREATED);
    }
//permite editar vuelos con codigos unicos, sino arroja un 403
    @PutMapping("/edit")
    public ResponseEntity<?> updateFlight(@RequestBody FlightDTO updateFlight, @RequestParam String flightNumber) {
        return new ResponseEntity<>(flightService.updateFlight(updateFlight, flightNumber), HttpStatus.OK);

    }

    @PutMapping("/delete")
    public ResponseEntity<?> deleteFlight(@RequestParam String flightNumber) {
        flightService.deleteFlightByCode(flightNumber);
        return ResponseEntity.ok("Vuelo No. " + flightNumber + " eliminado");
    }


}