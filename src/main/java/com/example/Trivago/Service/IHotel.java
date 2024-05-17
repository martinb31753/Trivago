package com.example.Trivago.Service;

import com.example.Trivago.DTO.HotelDTO;
import com.example.Trivago.Model.Hotel;

import java.time.LocalDate;
import java.util.List;

public interface IHotel {

    List<HotelDTO> getAll();

    List<HotelDTO> getAvailableHotels(LocalDate dateFrom, LocalDate dateTo, String destination);

}
