package com.example.Trivago.Repository;

import com.example.Trivago.Model.Flight;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.PropertyNamingStrategies;
import org.springframework.stereotype.Repository;
import org.springframework.util.ResourceUtils;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.List;

@Repository
public class FlightRepositoryImpl implements IFlightRepository {

    private List<Flight> flightList;

    public FlightRepositoryImpl() {
        flightList = loadData();
    }
    private List<Flight> loadData() {
        List<Flight> loadedData = null;
        File file;

        ObjectMapper objectMapper = new ObjectMapper().setPropertyNamingStrategy(PropertyNamingStrategies.SNAKE_CASE);

        TypeReference<List<Flight>> typeRef = new TypeReference<>() {};
        try {
            file = ResourceUtils.getFile("classpath:flight.json");
            loadedData = objectMapper.readValue(file, typeRef);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
            System.out.println("Error al cargar Json");
        } catch (IOException e) {
            e.printStackTrace();
            System.out.println("Error");
        }
        return loadedData;
    }

    @Override
    public List<Flight> getAll() {
        return flightList;
    }

    @Override
    public Flight getById(String flightCode) {
        return null;
    }
}