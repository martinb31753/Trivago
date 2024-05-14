package com.example.Trivago.Service;

import com.example.Trivago.DTO.HotelDTO;
import com.example.Trivago.Model.Hotel;
import com.example.Trivago.Repository.IHotelRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

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
}
