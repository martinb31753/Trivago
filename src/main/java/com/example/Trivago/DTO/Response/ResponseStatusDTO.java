package com.example.Trivago.DTO.Response;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor

public class ResponseStatusDTO {
     @JsonProperty("code")
    private Integer status;
     @JsonProperty("message")
    private String message;

}
