package com.example.Trivago.DTO;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.validation.constraints.Future;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class FlightDTO {
    @JsonProperty("flight_number")
    @NotBlank(message = "El campo número de vuelo no debe ser nulo ni vacío")
    @Pattern(regexp = "^[A-Z]{4}-\\d{4}$", message = "El número de vuelo debe respetar el siguiente formato AAAA-0000")
    private String flightNumber;

    @Size(min = 3, max = 30, message= "La localidad debe tener más tres caracteres y menos de treinta")
    @NotBlank(message = "El campo origen no debe ser nulo ni vacío")
    @JsonProperty("origin")
    private String origin;

    @Size(min = 3, max = 30, message= "La localidad debe tener más tres caracteres y menos de treinta")
    @NotBlank(message = "El campo destino no debe ser nulo ni vacío")
    @JsonProperty("destination")
    private String destination;

    @Size(min = 3, max = 30, message= "El tipo de siento debe tener más tres caracteres y menos de treinta")
    @NotBlank(message = "El campo tipo de asiento no debe ser nulo ni vacío")
    @Pattern(regexp = "^(Business|Economy)$", message = "El tipo de asiento debe ser 'Business' o 'Economy'")
    @JsonProperty("seat_type")
    private String seatType;

    @NotBlank(message = "El campo precio por persona no debe ser nulo ni vacío")
    @Pattern(regexp = "^(1000|[1-9]\\d{3,})(\\.\\d{1,2})?$", message = "El precio por persona debe ser mayor a $1000")
    @JsonProperty("price_per_person")
    private String pricePerPerson;

    @Future(message = "La fecha de ida debe ser posterior a la actual")
    @Pattern(regexp = "^(0[1-9]|[12][0-9]|3[01])-(0[1-9]|1[0-2])-\\d{4}$", message = "La fecha debe tener el formato dd-MM-yyyy")
    @JsonFormat(pattern = "dd-MM-yyyy")
    @JsonProperty("date_from")
    private LocalDate dateFrom;

    @Future(message = "La fecha de vuelta debe ser posterior a la actual")
    @Pattern(regexp = "^(0[1-9]|[12][0-9]|3[01])-(0[1-9]|1[0-2])-\\d{4}$", message = "La fecha debe tener el formato dd-MM-yyyy")
    @JsonFormat(pattern = "dd-MM-yyyy")
    @JsonProperty("date_to")
    private LocalDate dateTo;
}
