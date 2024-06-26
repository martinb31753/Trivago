package com.example.Trivago.Repository;

import com.example.Trivago.DTO.HotelDTO;
import com.example.Trivago.Model.Hotel;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.PropertyNamingStrategy;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import org.springframework.stereotype.Repository;
import org.springframework.util.ResourceUtils;

import java.io.File;
import java.io.IOException;
import java.time.LocalDate;
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
        } catch (IOException ex) {
            ex.printStackTrace();
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
        return hotelsList.stream()
                .filter(hotel -> hotel.getHotelCode().equals(hotelCode))
                .findFirst()
                .orElse(null);
    }

    @Override
    public boolean save(Hotel hotel) {
        hotelsList.removeIf(existingHotel -> existingHotel.getHotelCode().equals(hotel.getHotelCode()));

        return hotelsList.add(hotel);
    }

    @Override
    public boolean update(Hotel hotel) {
        Hotel foundHotel = getById(hotel.getHotelCode());

        hotelsList.remove(foundHotel);

        return hotelsList.add(hotel);
    }

    @Override
    public boolean delete(String hotelCode) {

        Hotel foundHotel = getById(hotelCode);

        return hotelsList.remove(foundHotel);
    }


    @Override
    public List<Hotel> getHotelsAvailableFilter(LocalDate date_from, LocalDate date_to, String destination) {
        return hotelsList.stream()
                .filter(hotel ->
                                (destination == null || hotel.getDestination().equalsIgnoreCase(destination)) &&
                                (date_from == null || isWithinDateRange(hotel.getDateFrom(), date_from, date_to)) &&
                                (date_to == null || isWithinDateRange(hotel.getDateTo(), date_from, date_to))
                )
                .toList();
    }

    private boolean isWithinDateRange(LocalDate date, LocalDate rangeStart, LocalDate rangeEnd) {
        return !date.isBefore(rangeStart) && !date.isAfter(rangeEnd);
    }

}
