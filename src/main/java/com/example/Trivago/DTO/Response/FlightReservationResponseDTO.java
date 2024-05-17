package com.example.Trivago.DTO.Response;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class FlightReservationResponseDTO {
    @JsonProperty("user_name")
    private String userName;

    @JsonProperty("amount")
    private double amount;

    @JsonProperty("interest")
    private double interest;

    @JsonProperty("total")
    private double total;

    @JsonProperty("flight_reservation")
    private FlightReservationResponseDetailDTO flightReservationDTO;

    @JsonProperty("status_code")
    private ResponseStatusDTO status;
}
