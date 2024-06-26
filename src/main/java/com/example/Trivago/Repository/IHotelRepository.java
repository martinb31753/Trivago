package com.example.Trivago.Repository;

import com.example.Trivago.DTO.HotelDTO;
import com.example.Trivago.Model.Hotel;

import java.time.LocalDate;
import java.util.List;

public interface IHotelRepository  {
    List<Hotel> getAll();

    Hotel getById(String hotelCode);
    List<Hotel> getHotelsAvailableFilter(LocalDate date_from, LocalDate date_to, String destination);

   boolean save(Hotel hotel);

    boolean update(Hotel hotel);

    boolean delete (String hotelCode);
}