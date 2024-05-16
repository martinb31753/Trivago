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
    private IHotelRepository hotelRepository;



    @Override
    public BookingResponseDTO bookHotelresponse(BookingRequestDTO request) {
        return null;
    }

    @Override
    public BookingResponseDTO bookHotelresponse(BookingRequestDTO request) {
        LocalDate dateFrom = request.getDateFrom();
        LocalDate dateTo = request.getDateTo();

        // Encontrar el hotel por código
        Hotel hotel = hotelRepository.getById(request.getHotelCode());
        System.out.println(request.getHotelCode());
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
        response.setUserName(request.getUserName());

        response.getBooking()
        response.getBookingResponseDetailDTO().setInterest(interest);
        response.getBookingResponseDetailDTO().setTotal(total);
        response.getBookingResponseDetailDTO().setDate_from(dateFrom);
        response.getBookingResponseDetailDTO().setDate_to(dateTo);
        response.getBookingResponseDetailDTO().setDestination(request.getDestination());
        response.getBookingResponseDetailDTO().setHotelCode(request.getHotelCode());
        response.getBookingResponseDetailDTO().setPeopleAmount(request.getPeopleAmount());
        response.getBookingResponseDetailDTO().setRoomType(request.getRoomType());
        response.getBookingResponseDetailDTO().setPeople(request.getPeople());


    }
