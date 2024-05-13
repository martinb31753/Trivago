package com.example.Trivago.Model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class PaymentMethod {

    private String type;
    private String card_number;
    private Integer dues;
    private Double interest;
}
