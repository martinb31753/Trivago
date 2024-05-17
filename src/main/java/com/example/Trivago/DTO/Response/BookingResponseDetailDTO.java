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

public class BookingResponseDetailDTO {
    @JsonProperty("amount")
    private double amount;
    @JsonProperty("interest")
    private double interest;
    @JsonProperty("total")
    private double total;

    @JsonFormat(pattern = "dd-MM-yyyy")
    @JsonProperty("date_from")
    private LocalDate dateFrom;

    @JsonProperty("date_to")
    @JsonFormat(pattern = "dd-MM-yyyy")
    private LocalDate dateTo;

    private String destination;
    @JsonProperty("hotel_code")
    private String hotelCode;
    @JsonProperty("people_amount")
    private int peopleAmount;
    @JsonProperty("room_type")
    private String roomType;
    private List<PersonDTO> people;
    @JsonProperty("payment_method")
    private PaymentMethodDTO paymentMethod;
}
