package com.example.Trivago.Service;

import com.example.Trivago.DTO.Request.BookingRequestDTO;
import com.example.Trivago.DTO.Response.BookingResponseDTO;
import com.example.Trivago.Entity.HotelBooking;

import java.util.List;

public interface IHotelBookingService {


    BookingResponseDTO bookHotelresponse(BookingRequestDTO request);

    List<HotelBooking> findAll();
}
