package com.example.Trivago.DTO.Request;

import com.example.Trivago.DTO.Response.FlightReservationResponseDetailDTO;
import com.example.Trivago.DTO.Response.ResponseStatusDTO;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class FlightReservationRequestDTO {
    @JsonProperty("user_name")
    private String userName;

    @JsonProperty("amount")
    private double amount;

    @JsonProperty("interest")
    private double interest;

    @JsonProperty("total")
    private double total;

    @JsonProperty("flight_reservation")
    private FlightReservationRequestDetailDTO flightReservationDTO;

    @JsonProperty("status_code")
    private ResponseStatusDTO status;
}
