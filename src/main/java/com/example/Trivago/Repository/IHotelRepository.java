package com.example.Trivago.Repository;

import com.example.Trivago.DTO.FlightDTO;
import com.example.Trivago.DTO.HotelDTO;
import com.example.Trivago.Model.Hotel;

import java.util.List;
import java.util.Optional;

public interface IHotelRepository  {
    List<Hotel> getAll();

    Hotel getById(String hotel_code);

    void save(Hotel hotel);

    Hotel save(HotelDTO newHotel);

    Hotel update(String hotelCode, HotelDTO updateHotel);

    Hotel remove(String hotelCode);
}