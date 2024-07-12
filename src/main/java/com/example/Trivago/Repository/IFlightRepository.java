package com.example.Trivago.Repository;

import com.example.Trivago.Entity.Flight;

import com.example.Trivago.Entity.Hotel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

public interface IFlightRepository extends JpaRepository<Flight,Long> {
    @Query("SELECT h FROM Flight h " +
            "WHERE h.dateFrom <= :dateFrom " +
            "AND h.dateTo >= :dateTo " +
            "AND h.origin = :origin " +
            "AND h.destination = :destination " +
            "AND h.isActive = true")
    List<Flight> getFlightsAvailableFilter(LocalDate dateFrom,
                                          LocalDate dateTo,
                                          String origin,
                                          String destination);

    @Query("SELECT f FROM Flight f WHERE f.flightNumber = :flightNumber AND f.isActive = true")
    Optional<Flight> findByFlightNumber(@Param("flightNumber") String flightNumber);

    Optional<Flight> findByFlightNumberAndSeatType(String flightNumber, String seatType);

    @Query("SELECT COUNT(fb) FROM FlightBooking fb JOIN fb.flight f WHERE f.flightNumber = :flightNumber")
    Long countByFlightNumber(@Param("flightNumber") String flightNumber);

    @Query("SELECT f FROM Flight f WHERE f.isActive = true")
    List<Flight> getAllFlightsIsActive();
}