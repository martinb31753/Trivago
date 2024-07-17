package com.example.Trivago.Entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@AllArgsConstructor
@NoArgsConstructor

@Entity
@Table(name = "hotels")
public class Hotel {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(name = "hotel_code")
    private String hotelCode;
    @Column(name = "name")
    private String name;
    @Column(name = "destination")
    private String destination;
    @Column(name = "room_type")
    private String roomType;
    @Column(name ="price_per_night")
    private String pricePerNight;

    @Column(name = "date_from")
    @JsonFormat(pattern = "dd-MM-yyyy")
    private LocalDate dateFrom;

    @Column(name = "date_to")
    @JsonFormat(pattern = "dd-MM-yyyy")
    private LocalDate dateTo;

    @Column(name = "is_reserved",columnDefinition = "boolean default false")
    private Boolean isReserved = false;

    @Column(name = "is_active",columnDefinition = "boolean default true")
    private Boolean isActive = true;

    // Relación uno a uno con HotelBooking


    @OneToOne(mappedBy = "hotel", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private HotelBooking hotelBooking;

}





