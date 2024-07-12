package com.example.Trivago.Controller;

import com.example.Trivago.DTO.Request.BookingRequestDTO;
import com.example.Trivago.DTO.Request.FlightReservationRequestDTO;
import com.example.Trivago.DTO.Response.BookingResponseDTO;
import com.example.Trivago.DTO.Response.RespuestaDTO;
import com.example.Trivago.Entity.FlightBooking;
import com.example.Trivago.Exception.InvalidReservationFlight;
import com.example.Trivago.Service.IHotelBookingService;

import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("/api/v1/hotel-booking")
@Validated
public class BookingController {

    @Autowired
    IHotelBookingService hotelService;

    @PostMapping("/new")
    public ResponseEntity<?> bookHotel(@Valid @RequestBody BookingRequestDTO request){
        BookingResponseDTO hotelBooking = hotelService.bookHotelresponse(request);
        return ResponseEntity.ok(hotelBooking);
    }
    @PutMapping("/edit")
    public ResponseEntity<?> editHotelReservation(@RequestBody @Valid BookingRequestDTO editReservation,
                                                   @RequestParam Long id) {
        return new ResponseEntity<>(hotelService.updateHotelReservation(editReservation, id), HttpStatus.OK);
    }

    @DeleteMapping("/delete")
    public ResponseEntity<?> deleteHotelBooking(@RequestParam("id") Long id) {
        hotelService.cancelBooking(id);
        return ResponseEntity.ok("Booking No. " + id + " eliminado con exito");
    }
}