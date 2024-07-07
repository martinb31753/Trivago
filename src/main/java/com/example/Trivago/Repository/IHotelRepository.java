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
            "WHERE h.dateFrom <= :dateFrom " +
            "AND h.dateTo >= :dateTo " +
            "AND h.destination = :destination " +
            "AND h.isActive = true " +
            "AND h.isReserved = false")
    List<Hotel> getHotelsAvailableFilter(LocalDate dateFrom,
                                         LocalDate dateTo,
                                         String destination);

    @Query("SELECT h FROM Hotel h WHERE h.hotelCode = :hotelCode")
    Optional<Hotel> getByHotelCode(@PathVariable("hotelCode") String hotelCode);
}