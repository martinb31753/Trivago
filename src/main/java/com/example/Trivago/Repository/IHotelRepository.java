package com.example.Trivago.Repository;

import com.example.Trivago.Entity.Hotel;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

public interface IHotelRepository extends JpaRepository<Hotel,Long> {

    List<Hotel> getAll();

    Hotel getById(String hotelCode);
    List<Hotel> getHotelsAvailableFilter(LocalDate date_from, LocalDate date_to, String destination);

    Hotel save(Hotel hotel);

    boolean update(Hotel hotel);

    Optional<Hotel> getByCode(String hotelCode);
}