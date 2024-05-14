package com.example.Trivago.Service;
import com.example.Trivago.DTO.FlightDTO;
import com.example.Trivago.Model.Flight;
import com.example.Trivago.Repository.IFlightRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;


@Service
public class FlightServiceImpl implements IFlight {
    @Autowired
    private IFlightRepository flightRepository;

    ModelMapper modelMapper = new ModelMapper();
    @Override
    public List<FlightDTO> getAll() {
        List<Flight> flightList = flightRepository.getAll();

        return flightList.stream()
                .map(flight -> modelMapper.map(flight, FlightDTO.class)).collect(java.util.stream.Collectors.toList());
    }

    @Override
    public List<FlightDTO> getFlightByDate(LocalDate dateFrom, LocalDate dateTo, String origin, String destination) {
        List<Flight> flights = flightRepository.getFlightByDateFilter(dateFrom, dateTo, origin, destination);

        return flights.stream()
                .map(flight -> modelMapper.map(flight, FlightDTO.class))
                .collect(Collectors.toList());
    }

}
