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

}