package com.example.Trivago.DTO;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class FlightDTO {
    private String flight_number;
    private String origin;
    private String destination;
    private String seat_type;
    private String price_per_person;

    @JsonFormat(pattern = "dd-MM-yyyy")
    private LocalDate date_from;

    @JsonFormat(pattern = "dd-MM-yyyy")
    private LocalDate date_to;
}
