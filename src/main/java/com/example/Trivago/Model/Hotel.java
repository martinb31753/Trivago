package com.example.Trivago.Model;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Hotel {

    private String hotel_code;
    private String name;
    private String destination;
    private String room_type;
    private String price_per_night;

    @JsonFormat(pattern = "dd-MM-yyyy")
    private LocalDate date_from;

    @JsonFormat(pattern = "dd-MM-yyyy")
    private LocalDate date_to;

    private Boolean is_reserved;


}
