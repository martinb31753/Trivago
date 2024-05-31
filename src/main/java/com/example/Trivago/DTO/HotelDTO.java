package com.example.Trivago.DTO;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class HotelDTO {
    @JsonProperty("hotel_code")
    private String hotelCode;

    @JsonProperty("name")
    private String name;

    @JsonProperty("destination")
    private String destination;

    @JsonProperty("room_type")
    private String roomType;

    @JsonProperty("price_per_night")
    private String pricePerNight;

    @JsonFormat(pattern = "dd-MM-yyyy")
    @JsonProperty("date_from")
    private LocalDate dateFrom;

    @JsonFormat(pattern = "dd-MM-yyyy")
    @JsonProperty("date_to")
    private LocalDate dateTo;

    @JsonProperty("is_reserved")
    private Boolean isReserved;

}
