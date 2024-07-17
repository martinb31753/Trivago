package com.example.Trivago.DTO;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@AllArgsConstructor
@NoArgsConstructor
@JsonPropertyOrder({"date", "totalIncome"})
public class IncomeDTO {
    @JsonProperty("date")
    private String date;

    @JsonProperty("totalIncome")
    private Double totalIncome;
}
