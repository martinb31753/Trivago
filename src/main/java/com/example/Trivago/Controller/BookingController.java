package com.example.Trivago.Controller;

import com.example.Trivago.DTO.Request.BookingRequestDTO;
import com.example.Trivago.DTO.Response.BookingResponseDTO;
import com.example.Trivago.Entity.FlightBooking;
import com.example.Trivago.Entity.HotelBooking;
import com.example.Trivago.Service.IHotelBookingService;

import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@RequestMapping("/api/v1")
@Validated
public class BookingController {

    @Autowired
    private IHotelBookingService hotelService;

    @PostMapping("/booking")
    public ResponseEntity<?> bookHotel(@Valid @RequestBody BookingRequestDTO request){
        BookingResponseDTO hotelBooking = hotelService.bookHotelresponse(request);
        return ResponseEntity.ok(hotelBooking);
    }

    @GetMapping("/hotels-bookings")
    public ResponseEntity<List<HotelBooking>> getFlights() {
        List<HotelBooking> flights = hotelService.findAll();
        return ResponseEntity.ok(flights);
    }
}