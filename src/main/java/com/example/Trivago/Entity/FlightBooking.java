package com.example.Trivago.Entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.time.LocalDateTime;
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

        @ManyToOne(fetch = FetchType.LAZY)
        @JoinColumn(name = "customer_id")
        private Customer customerId;

        @Column(name = "is_active",columnDefinition = "boolean default true")
        private Boolean isActive = true;

        @Column(name = "paymentMethod")
        private String paymentMethod;

        @Column(name = "number_card")
        private String numberCard;

        @Column(name = "dues")
        private Integer dues;


        @Column(name = "amount")
        private Double amount;

        @Column(name = "created_at", columnDefinition = "TIMESTAMP DEFAULT CURRENT_TIMESTAMP",  updatable = false)
        @JsonFormat(pattern = "dd-MM-yyyy HH:mm:ss")
        private LocalDateTime created_at = LocalDateTime.now();

        @PrePersist
        protected void onCreate() {
                created_at = LocalDateTime.now();
        }

        @ManyToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
        @JoinTable(name = "flight_booking_people",
                joinColumns = @JoinColumn(name = "flight_booking_id"),
                inverseJoinColumns = @JoinColumn(name = "people_id"))
        private Set<People> people;

//        @Column(name = "flight_id")
//        private Long flightId;

        @ManyToOne(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
        @JoinColumn(name = "flight_id")
        private Flight flight;

}
