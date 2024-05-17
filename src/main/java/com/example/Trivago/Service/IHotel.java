package com.example.Trivago.Service;

import com.example.Trivago.DTO.HotelDTO;
import com.example.Trivago.Model.Hotel;

import java.util.List;

public interface IHotel {

    List<HotelDTO> getAll();
}
