package com.example.Trivago.Model;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class PaymentMethod {

    private String type;
    @JsonProperty("card_number")
    private String cardNumber;
    private Integer dues;
    private Double interest;
}
