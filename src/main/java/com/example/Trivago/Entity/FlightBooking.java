package com.example.Trivago.Entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.util.Set;

@Data
@AllArgsConstructor
@NoArgsConstructor

@Entity
@Table(name = "flight_bookings")
public class FlightBooking {
        @Id
        @GeneratedValue(strategy = GenerationType.IDENTITY)
        private Long id;

        @Column(name = "seats")
        private int seats;

        @Column(name = "is_active",columnDefinition = "boolean default true")
        private Boolean isActive = true;

        @Column(name = "paymentMethod")
        private String paymentMethod;

        @Column(name = "number_card")
        private String numberCard;

        @Column(name = "dues")
        private Integer dues;

        @ManyToOne(fetch = FetchType.LAZY)
        @JoinColumn(name = "customer_id")
        private Customer customer;

        @ManyToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
        @JoinTable(name = "flight_booking_people",
                joinColumns = @JoinColumn(name = "flight_booking_id"),
                inverseJoinColumns = @JoinColumn(name = "people_id"))
        private Set<People> people;

        @ManyToOne(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
        @JoinColumn(name = "flight_id")
        private Flight flight;

}
