package com.example.Trivago.Repository;

import com.example.Trivago.Entity.Flight;
import com.example.Trivago.Entity.FlightBooking;
import com.example.Trivago.Entity.HotelBooking;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface IFlightBookingRepository extends JpaRepository<FlightBooking,Long> {
    @Query("SELECT fb FROM FlightBooking fb WHERE fb.isActive = true")
    List<FlightBooking> getFlightBookingsIsActive();

}
