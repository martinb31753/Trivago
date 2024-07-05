package com.example.Trivago.Service;

import com.example.Trivago.DTO.HotelDTO;
import com.example.Trivago.DTO.Response.RespuestaDTO;

import java.time.LocalDate;
import java.util.List;

public interface IHotel {

    RespuestaDTO deleteHotelByCode(String hotelCode);

    List<HotelDTO> getAvailableHotels(LocalDate dateFrom, LocalDate dateTo, String destination);

    List<HotelDTO> getAll();

    RespuestaDTO addNewHotel(HotelDTO hotelDTO);

    RespuestaDTO updateHotelById(HotelDTO updateHotel);
}
