package com.example.Trivago.Repository;

import com.example.Trivago.Model.Flight;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.PropertyNamingStrategies;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import org.springframework.stereotype.Repository;
import org.springframework.util.ResourceUtils;

import java.io.File;
import java.io.IOException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.stream.Collectors;

@Repository
public class FlightRepositoryImpl implements IFlightRepository {

    private List<Flight> flightList;

    public FlightRepositoryImpl() {
        flightList = loadData();
    }
    private List<Flight> loadData() {
        List<Flight> loadedData = null;
        File file;

        ObjectMapper objectMapper = new ObjectMapper()
                .setPropertyNamingStrategy(PropertyNamingStrategies.SNAKE_CASE)
                .registerModule(new JavaTimeModule());
        try {
            file = ResourceUtils.getFile("classpath:flight.json");
            loadedData = objectMapper.readValue(file, new TypeReference<>() {});
        } catch (IOException e) {
            e.printStackTrace();
            System.out.println("Error al cargar Json aviones");
        }
        return loadedData;
    }

    @Override
    public List<Flight> getAll() {
        return flightList;
    }

    @Override
    public List<Flight> getFlightByDateFilter(LocalDate date_from, LocalDate date_to, String origin, String destination) {
        return flightList.stream()
                .filter(flight ->
                        (origin == null || flight.getOrigin().equalsIgnoreCase(origin)) &&
                                (destination == null || flight.getDestination().equalsIgnoreCase(destination)) &&
                                (date_from == null || isWithinDateRange(flight.getDate_from(), date_from, date_to)) &&
                                (date_to == null || isWithinDateRange(flight.getDate_to(), date_from, date_to))
                )
                .collect(Collectors.toList());
    }

    private boolean isWithinDateRange(LocalDate date, LocalDate rangeStart, LocalDate rangeEnd) {
        return !date.isBefore(rangeStart) && !date.isAfter(rangeEnd);
    }

}