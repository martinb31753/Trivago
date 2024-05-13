package com.example.Trivago.Model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Flight {

    private String flight_number;
    private String origin;
    private String destination;
    private String seat_type;
    private String price_per_person;
    private LocalDate date_from;
    private LocalDate date_to;

}
