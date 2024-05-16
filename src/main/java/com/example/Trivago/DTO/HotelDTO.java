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
    private String name;
    private String destination;
    @JsonProperty("room_type")
    private String roomType;
    private String price_per_night;

    @JsonFormat(pattern = "dd-MM-yyyy")
    @JsonProperty("date_to")
    private LocalDate date_from;

    @JsonFormat(pattern = "dd-MM-yyyy")
    @JsonProperty("date_to")
    private LocalDate date_to;

    @JsonProperty("is_reserved")
    private Boolean isReserved;

}
