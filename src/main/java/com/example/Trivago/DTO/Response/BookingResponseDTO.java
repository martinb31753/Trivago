package com.example.Trivago.DTO.Response;

import com.example.Trivago.DTO.PaymentMethodDTO;
import com.example.Trivago.DTO.PersonDTO;
import com.example.Trivago.DTO.Request.BookingRequestDTO;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor

public class BookingResponseDTO {
    private String user_name;
    private double amount;
    private double interest;
    private double total;
   // private BookingRequestDTO booking;
   @JsonFormat(pattern = "dd-MM-yyyy")
   private LocalDate date_from;

    @JsonFormat(pattern = "dd-MM-yyyy")
    private LocalDate dateTo;

    private String destination;
    private String hotel_code;
    private int people_amount;
    private String room_type;
    private List<PersonDTO> people;
    private PaymentMethodDTO payment_method;


    }





