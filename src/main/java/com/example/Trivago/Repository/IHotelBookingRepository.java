package com.example.Trivago.Repository;

import com.example.Trivago.Entity.HotelBooking;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface IHotelBookingRepository extends JpaRepository<HotelBooking,Long> {
    @Query("SELECT hb FROM HotelBooking hb WHERE hb.isActive = true")
    List<HotelBooking> getHotelBookingsIsActive();
}
