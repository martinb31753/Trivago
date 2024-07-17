package com.example.Trivago.DTO;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;
@Data
@AllArgsConstructor
@NoArgsConstructor
public class AllHotelReservationsDTO {
    private List<HotelReservationDTO> hotelReservations;
}
