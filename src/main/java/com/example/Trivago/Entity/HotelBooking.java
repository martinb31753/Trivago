package com.example.Trivago.Entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.util.Set;

@Data
@NoArgsConstructor
@AllArgsConstructor

@Entity
@Table(name = "hotel_bookings")

public class HotelBooking {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "people_amount")
    private int peopleAmount;

    //cada reserva pertenece a un cliente
    @ManyToOne
    @JoinColumn(name = "customer_id")
    private Customer customer;

    @ManyToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @JoinTable(name = "hotel_booking_people",
            joinColumns = @JoinColumn(name = "hotel_booking_id"),
            inverseJoinColumns = @JoinColumn(name = "people_id"))
    private Set<People> people;

    //muchas reservas tienen una unica forma de pago
    @ManyToOne(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @JoinColumn(name = "payment_method_id")
    private PaymentMethod paymentMethod;

    // Relación uno a uno con Hotel, mapeado por el campo hotelBooking en Hotel
    @OneToOne
    @JoinColumn(name = "hotel_id")
    private Hotel hotel;


    @Column(name = "is_active",columnDefinition = "boolean default true")
    private Boolean isActive = true;
}
