package com.example.Trivago.DTO.Response;

import com.example.Trivago.DTO.PaymentMethodDTO;
import com.example.Trivago.DTO.PersonDTO;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class FlightReservationResponseDetailDTO {
    @JsonFormat(pattern = "dd-MM-yyyy")
    @JsonProperty("date_from")
    private LocalDate dateFrom;

    @JsonProperty("date_to")
    @JsonFormat(pattern = "dd-MM-yyyy")
    private LocalDate dateTo;

    @JsonProperty("origin")
    private String origin;

    @JsonProperty("destination")
    private String destination;

    @JsonProperty("flight_number")
    private String flightNumber;

    @JsonProperty("seats")
    private int seats;

    @JsonProperty("seat_type")
    private String seatType;

    @JsonProperty("people")
    private List<PersonDTO> people;

    @JsonProperty("payment_method")
    private PaymentMethodDTO paymentMethod;
}
