package com.example.Trivago.Service;

import com.example.Trivago.DTO.FlightDTO;
import com.example.Trivago.DTO.HotelDTO;
import com.example.Trivago.Model.Flight;
import com.example.Trivago.Model.Hotel;

import java.util.List;

public interface IHotel {

    List<HotelDTO> getAll();

    Hotel addNewHotel(HotelDTO newHotel);

    Hotel updateHotelById(String hotelCode, HotelDTO updateHotel);

    Hotel deleteHotelById(String hotelCode);
}
