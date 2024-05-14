package com.example.Trivago.Repository;

import com.example.Trivago.Model.Hotel;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.PropertyNamingStrategy;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import org.springframework.stereotype.Repository;
import org.springframework.util.ResourceUtils;

import java.io.File;
import java.io.IOException;
import java.util.List;

@Repository
public class HotelRepositoryImpl implements IHotelRepository {

    private List<Hotel> hotelsList;

    public HotelRepositoryImpl() {
        hotelsList = loadData();
    }

    private List<Hotel> loadData() {
        List<Hotel> loadedData = null;
        File file;

        ObjectMapper objectMapper = new ObjectMapper()
                .setPropertyNamingStrategy(PropertyNamingStrategy.SNAKE_CASE)
                .registerModule(new JavaTimeModule());


        try {
            file = ResourceUtils.getFile("classpath:hotel.json");
            loadedData = objectMapper.readValue(file, new TypeReference<>() {});
        } catch (IOException e) {
            e.printStackTrace();
            System.out.println("Error al cargar Json hoteles");
        }
        return loadedData;
    }

    @Override
    public List<Hotel> getAll() {
        return hotelsList;
    }

    @Override
    public Hotel getById(String hotelCode) {
        return null;
    }
}
