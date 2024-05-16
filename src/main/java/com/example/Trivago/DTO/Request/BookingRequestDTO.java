package com.example.Trivago.DTO.Request;

import com.example.Trivago.DTO.Response.BookingResponseDetailDTO;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class BookingRequestDTO {
    @JsonProperty("user_name")
    private String userName;
    private BookingRequestDetailDTO booking;


}