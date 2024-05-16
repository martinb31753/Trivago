package com.example.Trivago.Service;


import com.example.Trivago.DTO.PaymentMethodDTO;
import com.example.Trivago.DTO.Request.BookingRequestDTO;
import com.example.Trivago.DTO.Response.BookingResponseDTO;
import com.example.Trivago.DTO.Response.BookingResponseDetailDTO;
import com.example.Trivago.DTO.Response.ResponseStatusDTO;
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
        LocalDate dateFrom = request.getBooking().getDateFrom();
        LocalDate dateTo = request.getBooking().getDateTo();

        // Encontrar el hotel por código
        Hotel hotel = hotelRepository.getById(request.getBooking().getHotelCode());
        System.out.println(request.getBooking().getHotelCode());
        if (hotel == null) {
            throw new RuntimeException("Hotel no encontrado");

        }

        if (hotel.getIsReserved()) {
            throw new RuntimeException("El hotel ya está reservado");
        }

        // noche de la doble  $6300")
        double pricePerNight = Double.parseDouble(hotel.getPricePerNight().replace("$", ""));
        long numberOfNights = dateFrom.until(dateTo).getDays();
        double amount = pricePerNight * numberOfNights;

        // Interes
        double interest = 5.5;
        double total = amount + (amount * interest / 100);

        // Marcar  reservada
        hotel.setIsReserved(true);
        hotelRepository.save(hotel);

        BookingResponseDetailDTO bookingDetail = new BookingResponseDetailDTO();
        bookingDetail.setDateFrom(dateFrom);
        bookingDetail.setDateTo(dateTo);
        bookingDetail.setDestination(request.getBooking().getDestination());
        bookingDetail.setHotelCode(request.getBooking().getHotelCode());
        bookingDetail.setPeopleAmount(request.getBooking().getPeopleAmount());
        bookingDetail.setRoomType(request.getBooking().getRoomType());
        bookingDetail.setPeople(request.getBooking().getPeople());

        PaymentMethodDTO paymentMethod = new PaymentMethodDTO();
        paymentMethod.setType(request.getBooking().getPaymentMethod().getType());
        paymentMethod.setNumberCard(request.getBooking().getPaymentMethod().getNumberCard());
        paymentMethod.setDues(request.getBooking().getPaymentMethod().getDues());
        bookingDetail.setPaymentMethod(paymentMethod);

        ResponseStatusDTO status = new ResponseStatusDTO();
        status.setCode(201);
        status.setMessage("El proceso termino satisfactoriamente");

        BookingResponseDTO response = new BookingResponseDTO();
        response.setUserName(request.getUserName());
        response.setAmount(amount);
        response.setInterest(interest);
        response.setTotal(total);
        response.setBooking(bookingDetail);
        response.setStatus(status);

        return response;

    }
}