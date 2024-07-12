package com.example.Trivago.Controller;

import com.example.Trivago.DTO.Request.BookingRequestDTO;
import com.example.Trivago.DTO.Response.BookingResponseDTO;
import com.example.Trivago.Service.IHotelBookingService;

import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("/api/v1/hotel-booking")
@Validated
public class BookingController {

    @Autowired
    private IHotelBookingService hotelService;

    @PostMapping("/new")
    public ResponseEntity<?> bookHotel(@RequestBody BookingRequestDTO request){
        BookingResponseDTO hotelBooking = hotelService.bookHotelresponse(request);
        return ResponseEntity.ok(hotelBooking);
    }
    @DeleteMapping("/delete")
    public ResponseEntity<?> deleteHotelBooking(@RequestParam("id") Long id) {
        hotelService.cancelBooking(id);
        return ResponseEntity.ok("Booking No. " + id + " eliminado con exito");
    }
}