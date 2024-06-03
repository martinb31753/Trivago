package com.example.Trivago.Controller;

import com.example.Trivago.DTO.HotelDTO;
import com.example.Trivago.DTO.Response.ResponseStatusDTO;
import com.example.Trivago.Model.Hotel;
import com.example.Trivago.Service.IHotel;

import jakarta.validation.Valid;
import jakarta.validation.constraints.Pattern;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;


@RestController
@RequestMapping("/api/v1")
@Validated
public class HotelController {

    @Autowired
    IHotel hotelService;

    @GetMapping("/hotels")
    public ResponseEntity<?> getAvailableHotels(

            @Valid @RequestParam(value = "date_from", required = false) @DateTimeFormat(pattern = "dd-MM-yyyy") LocalDate date_from,

            @RequestParam(value = "date_to", required = false) @DateTimeFormat(pattern = "dd-MM-yyyy") @Pattern(regexp = "^(0[1-9]|[12][0-9]|3[01])-(0[1-9]|1[0-2])-\\d{4}$", message = "La fecha debe tener el formato dd-MM-yyyy") LocalDate date_to,

            @RequestParam(value = "destination", required = false) String destination) {
        List<HotelDTO> filteredHotels = hotelService.getAvailableHotels(date_from, date_to, destination);
        return ResponseEntity.ok(filteredHotels);
    }

    @PostMapping("/add-new-hotel")
    public ResponseEntity<?> addNewHotel(@RequestBody HotelDTO newHotel) {
        return new ResponseEntity<>( hotelService.addNewHotel(newHotel),HttpStatus.CREATED);
    }

    @PutMapping("/update-hotel/{hotelCode}")
    public ResponseEntity<?> updateHotelById(@RequestBody HotelDTO updateHotel, @PathVariable String hotelCode) {
        return new ResponseEntity<>(hotelService.updateHotelById(updateHotel), HttpStatus.OK);

    }

    @DeleteMapping("/remove-hotel/{hotelCode}")
    public ResponseEntity<?> deleteHotelById(@PathVariable String hotelCode) {
        return new ResponseEntity<>(hotelService.deleteHotelById(hotelCode), HttpStatus.OK);
    }
}