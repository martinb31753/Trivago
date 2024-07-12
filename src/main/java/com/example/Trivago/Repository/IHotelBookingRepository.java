package com.example.Trivago.Repository;

import com.example.Trivago.Entity.HotelBooking;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.time.LocalDate;
import java.time.LocalDateTime;

public interface IHotelBookingRepository extends JpaRepository<HotelBooking,Long> {
    @Query("SELECT SUM(h.amount) FROM HotelBooking h WHERE h.created_at BETWEEN :startOfDay AND :endOfDay")
    Double sumAmountByDate(@Param("startOfDay") LocalDateTime startOfDay, @Param("endOfDay") LocalDateTime endOfDay);
}
