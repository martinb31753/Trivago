package com.example.Trivago.Repository;

import com.example.Trivago.Model.Hotel;
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
public class HotelRepositoryImpl implements IHotelRepository {

    private List<Hotel> hotelsList;

    public HotelRepositoryImpl() {
        hotelsList = loadData();
    }

    private List<Hotel> loadData() {
        List<Hotel> loadedData = null;
        File file;

        ObjectMapper objectMapper = new ObjectMapper().setPropertyNamingStrategy(PropertyNamingStrategies.SNAKE_CASE);

        TypeReference<List<Hotel>> typeRef = new TypeReference<>() {};
        try {
            file = ResourceUtils.getFile("classpath:hotel.json");
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
    public List<Hotel> getAll() {
        return hotelsList;
    }

    @Override
    public Hotel getById(String hotelCode) {
        return null;
    }

    @Override
    public void save(Hotel hotel) {

    }
}
