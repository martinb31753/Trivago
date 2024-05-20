package com.example.Trivago.Service;

import com.example.Trivago.DTO.HotelDTO;
import com.example.Trivago.Model.Hotel;
import com.example.Trivago.Repository.IHotelRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class HotelServiceImpl implements IHotel {

    @Autowired
    private IHotelRepository hotelRepository;

    ModelMapper modelMapper = new ModelMapper();

    @Override
    public List<HotelDTO> getAll() {
        List<Hotel> hotelList = hotelRepository.getAll();
        return hotelList.stream()
                .map(hotel -> modelMapper.map(hotel, HotelDTO.class)).collect(java.util.stream.Collectors.toList());
    }

    @Override
    public List<HotelDTO> getAvailableHotels(LocalDate dateFrom, LocalDate dateTo, String destination) {
         List<Hotel> hotels = hotelRepository.getHotelsAvailableFilter(dateFrom, dateTo, destination);

        return hotels.stream()
                .map(flight -> modelMapper.map(flight, HotelDTO.class))
                .collect(Collectors.toList());
    }


    @Override
    public Hotel addNewHotel(HotelDTO newHotel) {
        return null;

    }

    @Override
    public Hotel updateHotelById(String hotelCode, HotelDTO updateHotel) {
        return null;
    }

    @Override
    public Hotel deleteHotelById(String hotelCode) {
        return null;
    }
}
