package com.example.Trivago.DTO.Request;

import com.example.Trivago.DTO.PaymentMethodDTO;
import com.example.Trivago.DTO.PersonDTO;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.validation.constraints.FutureOrPresent;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor

public class FlightReservationRequestDetailDTO {
    @NotNull(message = "La fecha 'date_from' no puede ser nula")
    @FutureOrPresent(message = "La fecha 'date_from' debe ser en el presente o en el futuro")
    @JsonFormat(pattern = "dd-MM-yyyy")
    @JsonProperty("date_from")
    private LocalDate dateFrom;

    @NotNull(message = "La fecha 'date_to' no puede ser nula")
    @FutureOrPresent(message = "La fecha 'date_to' debe ser en el presente o en el futuro")
    @JsonProperty("date_to")
    @JsonFormat(pattern = "dd-MM-yyyy")
    private LocalDate dateTo;

    @JsonProperty("origin")
    private String origin;

    @NotBlank(message = "El destino no puede estar en blanco")
    @JsonProperty("destination")
    private String destination;

    @JsonProperty("flight_number")
    private String flightNumber;

    //falta validación- con mensaje "La cantidad de personas debe ser un valor numérico"
    @JsonProperty("seats")
    private int seats;

    @JsonProperty("seat_type")
    private String seatType;

    @JsonProperty("people")
    private List<PersonDTO> people;

    @JsonProperty("payment_method")
    private PaymentMethodDTO paymentMethod;
}
