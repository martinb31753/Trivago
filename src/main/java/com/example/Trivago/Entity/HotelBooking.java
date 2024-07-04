package com.example.Trivago.Entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor

@Entity
@Table(name = "hotel_bookings")

public class HotelBooking {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name ="date_from")
    @JsonFormat(pattern = "dd-MM-yyyy")
    private LocalDate dateFrom;

    @Column(name = "date_to")
    @JsonFormat(pattern = "dd-MM-yyyy")
    private LocalDate dateTo;

    @Column(name = "destination")
    private String destination;

    @Column(name = "people_amount")
    private int peopleAmount;

    @Column(name = "room_type")
    private String roomType;

    @Column(name = "hotel_code")
    private String hotelCode;

    @Column(name = "is_active")
    private boolean isActive;

    //cada reserva pertenece a un solo cliente
    @ManyToOne
    @JoinColumn(name = "customer_id")
    private Customer customer;

    //muchas reservas tienen una unica forma de pago
    @ManyToOne
    @JoinColumn(name = "payment_method_id")
    private PaymentMethod paymentMethod;


    // Relación uno a uno con Hotel, mapeado por el campo hotelBooking en Hotel
    @OneToOne(mappedBy = "hotelBooking")
    private Hotel hotel;




}
