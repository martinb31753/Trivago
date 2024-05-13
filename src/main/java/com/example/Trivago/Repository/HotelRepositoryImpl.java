package com.example.Trivago.Repository;

import com.example.Trivago.Model.Hotel;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.module.SimpleModule;
import com.fasterxml.jackson.datatype.jsr310.deser.LocalDateDeserializer;
import com.fasterxml.jackson.datatype.jsr310.ser.LocalDateSerializer;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;
import org.springframework.stereotype.Repository;
import org.springframework.util.ResourceUtils;
import java.io.File;
import java.io.IOException;

@Repository
public class HotelRepositoryImpl implements IHotelRepository {

    private List<Hotel> hotelsList;

    public HotelRepositoryImpl() {
        hotelsList = loadData();
    }

    private List<Hotel> loadData() {
        List<Hotel> loadedData = null;
        File file;

        ObjectMapper objectMapper = new ObjectMapper();
        SimpleModule module = new SimpleModule();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        module.addDeserializer(LocalDate.class, new LocalDateDeserializer(formatter));
        module.addSerializer(LocalDate.class, new LocalDateSerializer(formatter));
        objectMapper.registerModule(module);

        try {
            file = ResourceUtils.getFile("classpath:hotel.json");
            loadedData = objectMapper.readValue(file, new TypeReference<>() {
            });
        } catch (IOException e) {
            e.printStackTrace();
            System.out.println("Error al cargar Json");
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
