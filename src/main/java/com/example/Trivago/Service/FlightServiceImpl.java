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

    private List<FlightDTO> flightList;
    ModelMapper modelMapper = new ModelMapper();

    @Override
    public List<FlightDTO> getAll() {
        return flightRepository.getAll().stream()
                .map(flight -> modelMapper.map(flight, FlightDTO.class)).toList();
    }

    @Override
    public List<FlightDTO> getFlightByDate(LocalDate date_from, LocalDate date_to, String origin, String destination) {
        flightList = getAll();
        if (origin == null && destination == null && date_from == null && date_to == null) {
            return flightList;
        }
        return flightList.stream()
                .filter(flight ->
                        (origin == null || flight.getOrigin().equalsIgnoreCase(origin)) &&
                                (destination == null || flight.getDestination().equalsIgnoreCase(destination)) &&
                                (date_from == null || isWithinDateRange(flight.getDateFrom(), date_from, date_to)) &&
                                (date_to == null || isWithinDateRange(flight.getDateTo(), date_from, date_to))
                )
                .toList();
    }

    private boolean isWithinDateRange(LocalDate date, LocalDate rangeStart, LocalDate rangeEnd) {
        return !date.isBefore(rangeStart) && !date.isAfter(rangeEnd);
    }


    @Override
    public FlightDTO getByFlightNumber(String flightCode) {
        return null;
    }

    @Override
    public Flight addNewFlight(FlightDTO flight) {
        return null;
    }

    @Override
    public Flight updateFlightById(Long id, FlightDTO updateFlight) {
        return null;
    }

    @Override
    public Flight deleteFlightById(Long id) {
        return null;
    }

}

