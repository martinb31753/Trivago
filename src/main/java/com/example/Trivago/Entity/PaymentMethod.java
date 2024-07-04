package com.example.Trivago.Entity;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor

@Entity
@Table(name = "payment_method")
public class PaymentMethod {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(name = "type")
    private String type;
    @Column(name = "number_card")
    private String numberCard;
    @Column(name = "dues")
    private Integer dues;

    //un método de pago puede estar asociado con muchas reservas de vuelo
    @OneToMany(mappedBy = "paymentMethod", cascade = CascadeType.ALL)
    private List<FlightBooking> flightBookings;

    //un método de pago puede estar asociado con muchas reservas de hoteles
    @OneToMany(mappedBy = "paymentMethod", cascade = CascadeType.ALL)
    private List<HotelBooking> hotelBookings;




}