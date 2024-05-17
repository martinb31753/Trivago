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
        try {
            List<HotelDTO> hotels = hotelService.getAll();

            if (hotels.isEmpty()) {
                return new ResponseEntity<>("No existen hoteles", HttpStatus.NO_CONTENT);
            } else {
                return new ResponseEntity<>(hotels, HttpStatus.OK);
            }
        } catch (Exception e) {
            return new ResponseEntity<>("Error inesperadoq", HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }



        @GetMapping("/filterHotels")
        public ResponseEntity<?> getAvailableHotels(
                @RequestParam(value = "date_from", required = false) @DateTimeFormat(pattern = "dd-MM-yyyy") LocalDate date_from,
                @RequestParam(value = "date_to", required = false) @DateTimeFormat(pattern = "dd-MM-yyyy") LocalDate date_to,
                @RequestParam(value = "destination", required = false) String destination) {

        try {
            List<HotelDTO> filteredHotels = hotelService.getAvailableHotels(date_from, date_to, destination);
            return ResponseEntity.ok(filteredHotels);
        } catch (Exception e) {
            return new ResponseEntity<>("Error inesperado", HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }


}