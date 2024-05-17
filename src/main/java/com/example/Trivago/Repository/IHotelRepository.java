package com.example.Trivago.Repository;

import com.example.Trivago.Model.Hotel;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

public interface IHotelRepository  {
    List<Hotel> getAll();

    Hotel getById(String hotelCode);
    List<Hotel> getHotelsAvailableFilter(LocalDate date_from, LocalDate date_to, String destination);


   

    void save(Hotel hotel);


}