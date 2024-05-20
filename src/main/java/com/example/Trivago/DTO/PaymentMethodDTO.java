package com.example.Trivago.DTO;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class PaymentMethodDTO {

    @JsonProperty("type")
    private String type;

    @JsonProperty("number_card")
    private String numberCard;

    @JsonProperty("dues")
    private Integer dues;
}
