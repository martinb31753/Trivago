package com.example.Trivago.Model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Hotel {

    private String hotelCode;
    private String name;
    private String destination;
    private String room_type;
    private String price_per_night;
    private LocalDate date_from;
    private LocalDate date_to;
    private Boolean is_reserved;

}
