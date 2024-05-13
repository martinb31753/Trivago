package com.example.Trivago.Model;

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
    private String lastName;
    private LocalDate birthdate;
    private String email;


}
