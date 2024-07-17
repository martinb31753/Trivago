package com.example.Trivago.Repository;

import com.example.Trivago.Entity.Hotel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

public interface IHotelRepository extends JpaRepository<Hotel,Long> {

    @Query("SELECT h FROM Hotel h " +
            "WHERE h.dateFrom BETWEEN :dateFrom " +
            "AND :dateTo " +
            "AND h.destination = :destination " +
            "AND h.isActive = true " +
            "AND h.isReserved = false")
    List<Hotel> getHotelsAvailableFilter(@Param("dateFrom") LocalDate dateFrom,
                                         @Param("dateTo") LocalDate dateTo,
                                         @Param("destination") String destination);

    @Query("SELECT h FROM Hotel h WHERE h.hotelCode = :hotelCode AND h.isActive = true ")
    Optional<Hotel> getByHotelCode(@Param("hotelCode") String hotelCode);

    @Query("SELECT h FROM Hotel h WHERE h.isActive = true")
    List<Hotel> getAllHotelIsActive();




}