package com.example.Trivago.Entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor

@Entity
@Table(name = "flight_bookings")
public class FlightBooking {
        @Id
        @GeneratedValue(strategy = GenerationType.IDENTITY)
        private Long id;

        @Column(name ="date_from")
        @JsonFormat(pattern = "dd-MM-yyyy")
        private LocalDate dateFrom;

        @Column(name = "date_to")
        @JsonFormat(pattern = "dd-MM-yyyy")
        private LocalDate dateTo;

        @Column(name = "origin")
        private String origin;

        @Column(name = "destination")
        private String destination;

        @Column(name = "flight_number")
        private String flightNumber;

        @Column(name = "seats")
        private int seats;

        @Column(name = "seat_type")
        private String seatType;

        //cada reserva pertenece a un solo cliente
        @ManyToOne
        @JoinColumn(name = "customer_id")
        private Customer customer;

        //muchas reservas tienen una unica forma de pago
        @ManyToOne
        @JoinColumn(name = "payment_method_id")
        private PaymentMethod paymentMethod;

        //cada reserva de vuelo está asociada con un solo vuelo
        @ManyToOne
        @JoinColumn(name = "flight_id")
        private Flight flight;


}
