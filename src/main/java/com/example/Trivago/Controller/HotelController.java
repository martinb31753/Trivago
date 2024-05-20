package com.example.Trivago.Controller;

import com.example.Trivago.DTO.HotelDTO;
import com.example.Trivago.DTO.Response.ResponseStatusDTO;
import com.example.Trivago.Model.Hotel;
import com.example.Trivago.Service.IHotel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;


@RestController
@RequestMapping("/api/v1")
public class HotelController {

    @Autowired
    IHotel hotelService;

    @GetMapping("/hotels")
    public ResponseEntity<?> getAllHotels() {
        List<HotelDTO> hotels = hotelService.getAll();
        return new ResponseEntity<>(hotels, HttpStatus.OK);
    }

    @PostMapping("/add-new-hotel")
    public ResponseEntity<?> addNewHotel(@RequestBody HotelDTO newHotel) {
        return new ResponseEntity<>( hotelService.addNewHotel(newHotel),HttpStatus.CREATED);
    }

    @PutMapping("/{hotelCode}")
    public ResponseEntity<?> updateHotelById(@PathVariable String hotelCode, @RequestBody HotelDTO updateHotel) {
        return new ResponseEntity<>(hotelService.updateHotelById(hotelCode, updateHotel), HttpStatus.OK);

    }

    @DeleteMapping("/{hotelCode}")
    public ResponseEntity<?> deleteHotelById(@PathVariable String hotelCode) {
        return new ResponseEntity<>(hotelService.deleteHotelById(hotelCode), HttpStatus.OK);
    }


    @GetMapping("/filterHotels")
    public ResponseEntity<?> getAvailableHotels(
            @RequestParam(value = "date_from", required = false) @DateTimeFormat(pattern = "dd-MM-yyyy") LocalDate date_from,
            @RequestParam(value = "date_to", required = false) @DateTimeFormat(pattern = "dd-MM-yyyy") LocalDate date_to,
            @RequestParam(value = "destination", required = false) String destination) {
        List<HotelDTO> filteredHotels = hotelService.getAvailableHotels(date_from, date_to, destination);
        return ResponseEntity.ok(filteredHotels);
    }


}