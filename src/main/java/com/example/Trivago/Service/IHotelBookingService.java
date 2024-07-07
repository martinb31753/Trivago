package com.example.Trivago.Service;

import com.example.Trivago.DTO.Request.BookingRequestDTO;
import com.example.Trivago.DTO.Response.BookingResponseDTO;
import com.example.Trivago.DTO.Response.RespuestaDTO;
import com.example.Trivago.Entity.HotelBooking;

public interface IHotelBookingService {


    BookingResponseDTO bookHotelresponse(BookingRequestDTO request);

    RespuestaDTO cancelBooking(Long id);
}
