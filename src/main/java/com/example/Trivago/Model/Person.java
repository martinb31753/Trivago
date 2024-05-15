package com.example.Trivago.Model;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Person {

    private String dni;
    private String name;
    @JsonProperty("last_name")
    private String lastName;
    @JsonProperty("birth_date")
    private LocalDate birthdate;
    private String email;


}
