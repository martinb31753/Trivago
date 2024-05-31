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

    private List<HotelDTO> hotelList;
    ModelMapper modelMapper = new ModelMapper();


    @Override
    public List<HotelDTO> getAll() {
        return hotelRepository.getAll()
                .stream()
                .map(hotel -> modelMapper.map(hotel, HotelDTO.class))
                .toList();
    }

    @Override
    public List<HotelDTO> getAvailableHotels(LocalDate dateFrom, LocalDate dateTo, String destination) {
        hotelList = getAll();
        if (destination == null && dateFrom == null && dateTo == null) {
            return hotelList;
        }
        return hotelList.stream().filter(hotel ->
                (destination == null || hotel.getDestination().equalsIgnoreCase(destination)) &&
                        (dateFrom == null || isWithinDateRange(hotel.getDateFrom(), dateFrom, dateTo)) &&
                        (dateTo == null || isWithinDateRange(hotel.getDateTo(), dateFrom, dateTo)))
                .toList();
    }

    private boolean isWithinDateRange(LocalDate date, LocalDate rangeStart, LocalDate rangeEnd) {
        return !date.isBefore(rangeStart) && !date.isAfter(rangeEnd);
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
