package com.example.Trivago.Repository;

import com.example.Trivago.Entity.HotelBooking;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.time.LocalDateTime;
import java.util.List;

public interface IHotelBookingRepository extends JpaRepository<HotelBooking,Long> {
    @Query("SELECT hb FROM HotelBooking hb WHERE hb.isActive = true")
    List<HotelBooking> getHotelBookingsIsActive();

    @Query("SELECT hb FROM HotelBooking hb " +
            "JOIN FETCH hb.customerId c " +
            "JOIN FETCH hb.people p " +
            "JOIN FETCH hb.hotel h")
    List<HotelBooking> findAllReservations();

    @Query("SELECT SUM(h.amount) FROM HotelBooking h WHERE h.created_at BETWEEN :startOfDay AND :endOfDay")
    Double sumAmountByDate(@Param("startOfDay") LocalDateTime startOfDay, @Param("endOfDay") LocalDateTime endOfDay);
}
