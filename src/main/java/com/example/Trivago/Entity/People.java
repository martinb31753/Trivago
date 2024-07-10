package com.example.Trivago.Entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.util.List;
import java.util.Set;

@Data
@AllArgsConstructor
@NoArgsConstructor

@Entity
@Table(name = "people")
public class People {
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

    @ManyToMany(mappedBy = "people", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private Set<HotelBooking> hotelBookings;

    @ManyToMany(mappedBy = "people", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private Set<FlightBooking> flightBookings;


}
