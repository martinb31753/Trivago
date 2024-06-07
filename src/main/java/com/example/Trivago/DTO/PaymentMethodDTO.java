package com.example.Trivago.DTO;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.Pattern;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class PaymentMethodDTO {

    @Pattern(regexp = "^(CREDIT|DEBIT)$", message = "El tipo de tarjeta debe ser credito o debito")
    @JsonProperty("type")
    private String type;

    @Pattern(regexp = "^[0-9]{4}-[0-9]{4}-[0-9]{4}-[0-9]{4}$",
            message = "El número de tarjeta debe tener 16 dígitos y puede contener guiones")
    @JsonProperty("number_card")
    private String numberCard;

    @Min(value = 1, message = "La cantidad de cuotas no puede ser menor a 1")
    @Max(value = 12, message = "La cantidad de cuotas no puede ser mayor a 12")
    @JsonProperty("dues")
    private Integer dues;
}
