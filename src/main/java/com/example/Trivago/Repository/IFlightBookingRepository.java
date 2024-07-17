package com.example.Trivago.Repository;

import com.example.Trivago.DTO.Response.FlightReservationResponseDTO;
import com.example.Trivago.Entity.Flight;
import com.example.Trivago.Entity.FlightBooking;
import com.example.Trivago.Entity.HotelBooking;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

public interface IFlightBookingRepository extends JpaRepository<FlightBooking,Long> {
    @Query("SELECT fb FROM FlightBooking fb WHERE fb.isActive = true")
    List<FlightBooking> getFlightBookingsIsActive();

    @Query("SELECT SUM(f.amount) FROM FlightBooking f WHERE f.created_at BETWEEN :startOfDay AND :endOfDay")
    Double sumAmountByDate(@Param("startOfDay") LocalDateTime startOfDay, @Param("endOfDay") LocalDateTime endOfDay);

}
