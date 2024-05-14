package com.example.Trivago.DTO;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class PaymentMethodDTO {

    private String type;
    private String card_number;
    private Integer dues;
}
