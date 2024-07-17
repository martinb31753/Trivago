package com.example.Trivago.DTO;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.util.List;
import java.util.Set;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class HotelReservationDTO {
    @JsonProperty("reservation_id")
    private Long reservationId;

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
    private Integer peopleAmount;

    @JsonProperty("room_type")
    private String roomType;

    private Set<PersonDTO> people;

    @JsonProperty("payment_method")
    private PaymentMethodDTO paymentMethodDto;
}
