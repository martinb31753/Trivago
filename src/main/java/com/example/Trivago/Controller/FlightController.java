package com.example.Trivago.Controller;
import com.example.Trivago.DTO.FlightDTO;
import com.example.Trivago.Model.Flight;
import com.example.Trivago.Service.IFlight;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;


import java.time.LocalDate;
import java.util.Date;
import java.util.List;

@RestController
@RequestMapping("/api/v1")
@Validated
public class FlightController {

    @Autowired
    IFlight flightService;

    @GetMapping("/flights")
    public ResponseEntity<?> getAvailableFlights(
            @RequestParam(value = "date_from", required = false) @DateTimeFormat(pattern = "dd-MM-yyyy") LocalDate date_from,
            @RequestParam(value = "date_to", required = false) @DateTimeFormat(pattern = "dd-MM-yyyy") LocalDate date_to,
            @RequestParam (value = "origin", required = false) String origin,
            @RequestParam (value = "destination", required = false) @NotBlank(message = "El campo destino no debe ser nulo ni vacío") @Size(min = 3, max = 30, message= "La localidad debe tener más tres caracteres y menos de treinta") String destination){
        List<FlightDTO> filteredFlights = flightService.getFlightByDate(date_from, date_to, origin, destination);
        return ResponseEntity.ok(filteredFlights);
    }

    @PostMapping("/add-new-flight")
    public ResponseEntity<?> addNewFlight(@RequestBody @Valid FlightDTO newFlight) {
        return new ResponseEntity<>(flightService.addNewFlight(newFlight), HttpStatus.CREATED);
    }

    @PutMapping("/update-flight/{flightNumber}")
    public ResponseEntity<?> updateFlight(@RequestBody @Valid FlightDTO updateFlight, @PathVariable String flightNumber) {
        return new ResponseEntity<>(flightService.updateFlight(updateFlight), HttpStatus.OK);

    }

    @DeleteMapping("/remove-flight/{flightNumber}")
    public ResponseEntity<?> deleteFlightById(@PathVariable String flightNumber) {
        return new ResponseEntity<>(flightService.deleteFlightById(flightNumber), HttpStatus.OK);
    }


}