package com.example.Trivago.Controller;

import com.example.Trivago.DTO.Request.BookingRequestDTO;
import com.example.Trivago.DTO.Response.BookingResponseDTO;
import com.example.Trivago.Service.IHotelBookingService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("/api/v1")
public class BookingController {

    @Autowired
    private IHotelBookingService hotelService;

    @PostMapping("/booking")
    public ResponseEntity<?> bookHotel(@RequestBody BookingRequestDTO request){
        BookingResponseDTO hotelBooking = hotelService.bookHotelresponse(request);
        return ResponseEntity.ok(hotelBooking);
    }
}