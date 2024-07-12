package com.example.Trivago.Entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "flight_bookings")
public class FlightBooking {
        @Id
        @GeneratedValue(strategy = GenerationType.IDENTITY)
        private Long id;

        @Column(name = "date_from")
        @JsonFormat(pattern = "dd-MM-yyyy")
        private LocalDate dateFrom;

        @Column(name = "date_to")
        @JsonFormat(pattern = "dd-MM-yyyy")
        private LocalDate dateTo;

        @Column(name = "destination")
        private String destination;

        @Column(name = "origin")
        private String origin;

        @Column(name = "people_amount")
        private int peopleAmount;

        @Column(name = "flight_number")
        private String flightNumber;

        @Column(name = "seat_type")
        private String seatType;

        @Column(name = "seats")
        private int seats;

        @Column(name = "is_active")
        private boolean isActive;

        @Column(name = "amount")
        private Double amount;

        @Column(name = "created_at", updatable = false)
        @JsonFormat(pattern = "dd-MM-yyyy HH:mm:ss")
        private LocalDateTime created_at;

        //cada reserva pertenece a un solo cliente
        @ManyToOne(fetch = FetchType.LAZY)
        @JoinColumn(name = "customer_id")
        private Customer customer;

        //muchas reservas tienen una unica forma de pago
        @ManyToOne(fetch = FetchType.LAZY)
        @JoinColumn(name = "payment_method_id")
        private PaymentMethod paymentMethod;

        @PrePersist
        protected void onCreate() {
                created_at = LocalDateTime.now();
        }
}