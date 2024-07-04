package com.example.Trivago.Repository;

import com.example.Trivago.Entity.FlightBooking;
import org.springframework.data.jpa.repository.JpaRepository;

public interface IFlightBookingRepository extends JpaRepository<FlightBooking,Long> {
}
