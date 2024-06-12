package com.example.Trivago.DTO.Request;

import com.example.Trivago.DTO.Response.FlightReservationResponseDetailDTO;
import com.example.Trivago.DTO.Response.ResponseStatusDTO;
import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.validation.Valid;
import jakarta.validation.constraints.Email;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class FlightReservationRequestDTO {
    @Email(message = "El email debe tener un formato valido, usuario@gmail.com.ar")
    @JsonProperty("user_name")
    private String userName;
    @JsonProperty("flight_reservation")
    @Valid
    private FlightReservationRequestDetailDTO flightReservationDTO;
}
