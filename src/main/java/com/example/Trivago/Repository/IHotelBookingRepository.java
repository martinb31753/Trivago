package com.example.Trivago.Repository;

import com.example.Trivago.Entity.HotelBooking;
import org.springframework.data.jpa.repository.JpaRepository;

public interface IHotelBookingRepository extends JpaRepository<HotelBooking,Long> {
}
