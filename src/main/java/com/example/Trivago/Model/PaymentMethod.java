package com.example.Trivago.Model;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class PaymentMethod {

    @JsonProperty("type")
    private String type;
    @JsonProperty("number_card")
    private String numberCard;
    @JsonProperty("dues")
    private Integer dues;
   
}
