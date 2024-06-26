package com.example.Trivago.DTO.Response;

import com.example.Trivago.DTO.PaymentMethodDTO;
import com.example.Trivago.DTO.PersonDTO;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@JsonPropertyOrder({"date_from", "date_to", "destination", "hotel_code", "people_amount", "room_type", "people"})
public class BookingResponseDetailDTO {
    
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
}
