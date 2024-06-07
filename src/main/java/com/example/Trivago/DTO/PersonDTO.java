package com.example.Trivago.DTO;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.validation.constraints.Email;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class PersonDTO {
    @JsonProperty("dni")
    private String dni;
    @JsonProperty("name")
    private String name;
    @JsonProperty("last_name")
    private String lastName;
    @JsonFormat(pattern = "dd-MM-yyyy")
    @JsonProperty("birth_date")
    private LocalDate birthDate;
    @JsonProperty("email")
    @Email(message = "El email de la persona debe tener un formato valido, usuario@gmail.com.ar")
    private String email;
}
