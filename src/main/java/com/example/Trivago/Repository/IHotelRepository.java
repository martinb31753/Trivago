package com.example.Trivago.Repository;

import com.example.Trivago.Model.Hotel;

import java.util.List;

public interface IHotelRepository {
    List<Hotel> getAll();
    Hotel getById(String hotelCode);
}