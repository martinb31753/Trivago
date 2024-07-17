package com.example.Trivago.Entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
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
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "customer_id")
    private Customer customerId;

    @ManyToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @JoinTable(name = "hotel_booking_people",
            joinColumns = @JoinColumn(name = "hotel_booking_id"),
            inverseJoinColumns = @JoinColumn(name = "people_id"))
    private Set<People> people;

    @Column(name = "payment_method")
    private String paymentMethod;

    @Column(name = "number_card")
    private String numberCard;

    @Column(name = "dues")
    private Integer dues;

    @Column(name = "amount")
    private Double amount;

    @Column(name = "created_at", columnDefinition = "TIMESTAMP DEFAULT CURRENT_TIMESTAMP", updatable = false, insertable = false)
    @JsonFormat(pattern = "dd-MM-yyyy HH:mm:ss")
    private LocalDateTime created_at;

    @PrePersist
    protected void onCreate() {
        if (created_at == null) {
            created_at = LocalDateTime.now();
        }
    }

    // Relación uno a uno con Hotel, mapeado por el campo hotelBooking en Hotel
    @OneToOne
    @JoinColumn(name = "hotel_id")
    private Hotel hotel;

    @Column(name = "is_active",columnDefinition = "boolean default true")
    private Boolean isActive = true;
}
