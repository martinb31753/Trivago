package com.example.Trivago.DTO;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.validation.constraints.Pattern;
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

    @Pattern(regexp = "^(0[1-9]|[12][0-9]|3[01])-(0[1-9]|1[0-2])-\\d{4}$", message = "La fecha debe tener el formato dd-MM-yyyy")
    @JsonFormat(pattern = "dd-MM-yyyy")
    @JsonProperty("date_from")
    private LocalDate dateFrom;

    @JsonFormat(pattern = "dd-MM-yyyy")
    @JsonProperty("date_to")
    private LocalDate dateTo;

    @JsonProperty("is_reserved")
    private Boolean isReserved;

}
