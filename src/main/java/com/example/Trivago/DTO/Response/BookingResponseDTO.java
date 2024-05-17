package com.example.Trivago.DTO.Response;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class BookingResponseDTO {
    @JsonProperty("user_name")
    private String userName;
    @JsonProperty("booking")
    private BookingResponseDetailDTO booking;



}





