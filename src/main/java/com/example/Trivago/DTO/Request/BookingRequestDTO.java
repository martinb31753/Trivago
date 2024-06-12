package com.example.Trivago.DTO.Request;

import com.example.Trivago.DTO.PaymentMethodDTO;
import com.example.Trivago.DTO.Response.BookingResponseDetailDTO;
import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.validation.Valid;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.NonNull;


@Data
@AllArgsConstructor
@NoArgsConstructor
public class BookingRequestDTO {


    @Email(message = "El email debe tener un formato valido, usuario@gmail.com.ar")
    @JsonProperty("user_name")
    private String userName;

    @JsonProperty("booking")
    @Valid
    private BookingRequestDetailDTO booking;
}