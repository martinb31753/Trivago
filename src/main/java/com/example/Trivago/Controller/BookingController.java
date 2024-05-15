package com.example.Trivago.Controller;

import com.example.Trivago.DTO.Request.BookingRequestDTO;
import com.example.Trivago.DTO.Response.BookingResponseDTO;
import com.example.Trivago.Service.IHotelBookingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@RestController
@RequestMapping("/api/v1")
public class BookingController {

   @Autowired
    private IHotelBookingService hotelService;



    @PostMapping("/booking")
    public BookingResponseDTO bookHotel(@RequestBody BookingRequestDTO request) {

        return hotelService.bookHotel(request);
    }
}