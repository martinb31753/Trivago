package com.example.Trivago.Service;

import com.example.Trivago.DTO.Request.BookingRequestDTO;
import com.example.Trivago.DTO.Response.BookingResponseDTO;

public interface IHotelBookingService {


    BookingResponseDTO bookHotelresponse(BookingRequestDTO request);
}
