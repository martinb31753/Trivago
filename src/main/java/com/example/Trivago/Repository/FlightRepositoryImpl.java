package com.example.Trivago.Repository;

import com.example.Trivago.DTO.FlightDTO;
import com.example.Trivago.Model.Flight;
import com.example.Trivago.Model.Hotel;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.PropertyNamingStrategies;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import org.springframework.stereotype.Repository;
import org.springframework.util.ResourceUtils;

import java.io.File;
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
    public Flight getByFlightNumber(String flightCode) {

        return flightList.stream().filter(flight -> flight.getFlightNumber()
                .equals(flightCode))
                .findFirst()
                .orElse(null);
    }

    @Override
    public boolean save(Flight flight) {
        flightList.removeIf(existingFlight -> existingFlight.getFlightNumber().equals(flight.getFlightNumber()));

        return flightList.add(flight);

    }


    @Override
    public boolean update(Flight flight) {

        Flight foundFlight = getByFlightNumber(flight.getFlightNumber());

        flightList.remove(foundFlight);

        return flightList.add(flight);

    }



    @Override
    public boolean delete(String flightNumber) {

        Flight foundFlight = getByFlightNumber(flightNumber);

        return flightList.remove(foundFlight);
    }

//    @Override
//    public Flight getById(String flightNumber) {
//        return flightList.stream().filter(flight -> flight.getFlightNumber()
//                .equals(flightNumber))
//                .findFirst().orElse(null);
//    }
}