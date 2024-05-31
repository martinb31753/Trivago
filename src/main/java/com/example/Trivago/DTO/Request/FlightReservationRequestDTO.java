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
    @JsonProperty("flight_reservation")
    private FlightReservationRequestDetailDTO flightReservationDTO;
}
