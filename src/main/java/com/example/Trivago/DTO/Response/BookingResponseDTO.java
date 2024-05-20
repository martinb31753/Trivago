package com.example.Trivago.DTO.Response;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.awt.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class BookingResponseDTO {
    @JsonProperty("user_name")
    private String userName;

    @JsonProperty("amount")
    private double amount;

    @JsonProperty("interest")
    private double interest;

    @JsonProperty("total")
    private double total;

    @JsonProperty("booking")
    private BookingResponseDetailDTO booking;

    @JsonProperty("status_code")
    private ResponseStatusDTO status;

}





