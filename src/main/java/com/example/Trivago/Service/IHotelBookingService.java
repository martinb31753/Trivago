package com.example.Trivago.Service;

import com.example.Trivago.DTO.AllHotelReservationsDTO;
import com.example.Trivago.DTO.Request.BookingRequestDTO;
import com.example.Trivago.DTO.Response.BookingResponseDTO;
import com.example.Trivago.DTO.Response.RespuestaDTO;
import com.example.Trivago.Entity.HotelBooking;

import java.util.List;

public interface IHotelBookingService {

    List<AllHotelReservationsDTO> findAllHotelBooking();

    BookingResponseDTO bookHotelresponse(BookingRequestDTO request);

    RespuestaDTO updateHotelReservation(BookingRequestDTO editReservation, Long id);

    RespuestaDTO cancelBooking(Long id);


}
