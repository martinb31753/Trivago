package com.example.Trivago.Service;


import com.example.Trivago.DTO.Request.BookingRequestDTO;
import com.example.Trivago.DTO.Response.BookingResponseDTO;
import com.example.Trivago.Model.Hotel;
import com.example.Trivago.Repository.IHotelRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;

@Service

public class HotelBookingServiceImpl implements IHotelBookingService {

    @Autowired
    private  IHotelRepository hotelRepository;





    @Override
    public BookingResponseDTO bookHotel(BookingRequestDTO request) {
        LocalDate dateFrom = request.getDateFrom();
        LocalDate dateTo = request.getDateTo();

        // Encontrar el hotel por código
        Hotel hotel = hotelRepository.getById(request.getHotel_code());
        if (hotel == null) {
            throw new RuntimeException("Hotel no encontrado");
        }

        if (hotel.getIs_reserved()) {
            throw new RuntimeException("El hotel ya está reservado");
        }


        // noche de la doble  $6300")
        double pricePerNight = Double.parseDouble(hotel.getPrice_per_night().replace("$", ""));
        long numberOfNights = dateFrom.until(dateTo).getDays();
        double amount = pricePerNight * numberOfNights;

        // Interes
        double interest = 5.5;
        double total = amount + (amount * interest / 100);

        // Marcar  reservada
        hotel.setIs_reserved(true);
        hotelRepository.save(hotel);

        //  respuesta
        BookingResponseDTO response = new BookingResponseDTO();
        response.setUser_name(request.getUser_name());
        response.setAmount(amount);
        response.setInterest(interest);
        response.setTotal(total);
        response.setDate_from(dateFrom);
        response.setDateTo(dateTo);
        response.setDestination(request.getDestination());
        response.setHotel_code(request.getHotel_code());
        response.setPeople_amount(request.getPeople_amount());
        response.setRoom_type(request.getRoomType());
        response.setPeople(request.getPeople());




        return response;
    }

}
