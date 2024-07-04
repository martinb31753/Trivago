package com.example.Trivago.Entity;

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
@Table(name = "customers")
public class Customer {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(name = "dni")
    private String dni;
    @Column(name = "name")
    private String name;
    @Column(name = "last_name")
    private String lastName;
    @Column(name = "birth_date")
    private LocalDate birthDate;
    @Column(name = "email")
    private String email;

    //un cliente puede tener muchas reservas
    @OneToMany(mappedBy = "customer", cascade = CascadeType.ALL)
    private List<FlightBooking> flightBookings;

    //un cliente puede tener muchas reservas
    @OneToMany(mappedBy = "customer", cascade = CascadeType.ALL)
    private List<HotelBooking> hotelBookings;



}
