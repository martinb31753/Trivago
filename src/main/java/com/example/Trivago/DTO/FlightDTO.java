package com.example.Trivago.DTO;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class FlightDTO {
    @JsonProperty("flight_number")
    private String flightNumber;
    @JsonProperty("origin")
    private String origin;
    @JsonProperty("destination")
    private String destination;
    @JsonProperty("seat_type")
    private String seatType;
    @JsonProperty("price_per_person")
    private String pricePerPerson;

    @JsonFormat(pattern = "dd-MM-yyyy")
    private LocalDate date_from;

    @JsonFormat(pattern = "dd-MM-yyyy")
    private LocalDate date_to;
}
