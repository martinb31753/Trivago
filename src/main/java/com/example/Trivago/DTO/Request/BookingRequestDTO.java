package com.example.Trivago.DTO.Request;

import com.example.Trivago.DTO.PaymentMethodDTO;
import com.example.Trivago.DTO.PersonDTO;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class BookingRequestDTO {
    private String user_name;

    @JsonFormat(pattern = "dd-MM-yyyy")
    private LocalDate dateFrom;

    @JsonFormat(pattern = "dd-MM-yyyy")
    private LocalDate dateTo;

    private String destination;
    private String hotel_code;
    private int people_amount;
    private String roomType;
    private List<PersonDTO> people;
    private PaymentMethodDTO payment_method;

}