package com.example.Trivago.DTO.Request;

import com.example.Trivago.DTO.PaymentMethodDTO;
import com.example.Trivago.DTO.PersonDTO;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.validation.Valid;
import jakarta.validation.constraints.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class BookingRequestDetailDTO {

    @NotNull(message = "La fecha 'date_from' no puede ser nula")
    @FutureOrPresent(message = "La fecha 'date_from' debe ser en el presente o en el futuro")
    @JsonFormat(pattern = "dd-MM-yyyy")
    @JsonProperty("date_from")
    private LocalDate dateFrom;

    @NotNull(message = "La fecha 'date_to' no puede ser nula")
    @FutureOrPresent(message = "La fecha 'date_to' debe ser en el presente o en el futuro")
    @JsonFormat(pattern = "dd-MM-yyyy")
    @JsonProperty("date_to")
    private LocalDate dateTo;

    @NotBlank(message = "El destino no puede estar en blanco")
    private String destination;

    @NotBlank(message = "El código de hotel no puede estar en blanco")
    @Size(min = 3, max = 10, message = "El código de hotel debe tener entre 3 y 10 caracteres")
    @JsonProperty("hotel_code")
    private String hotelCode;

    //falta validación- con mensaje "La cantidad de personas debe ser un valor numérico"
    @Positive(message = "La cantidad de personas debe ser mayor que cero")
    @JsonProperty("people_amount")
    private int peopleAmount;

    @NotBlank(message = "El tipo de habitación no puede estar en blanco")
    @JsonProperty("room_type")
    private String roomType;

    @NotNull(message = "La lista de personas no puede ser nula")
    @Size(min = 1, message = "Debe haber al menos una persona especificada")
    private List<@Valid PersonDTO> people;

    @NotNull(message = "El método de pago no puede ser nulo")
    @Valid
    @JsonProperty("payment_method")
    private PaymentMethodDTO paymentMethod;
}

