package com.example.Trivago.Service;


import com.example.Trivago.DTO.Request.BookingRequestDTO;
import com.example.Trivago.DTO.Response.BookingResponseDTO;
import com.example.Trivago.DTO.Response.BookingResponseDetailDTO;
import com.example.Trivago.DTO.Response.ResponseStatusDTO;
import com.example.Trivago.Exception.InvalidBookingHotel;
import com.example.Trivago.Exception.InvalidDate;
import com.example.Trivago.Exception.InvalidReservationFlight;
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
        // Encontrar el hotel por código

        System.out.println(request.getBooking().getHotelCode());
        Hotel hotel = hotelRepository.getById(request.getBooking().getHotelCode());

        System.out.println(hotel);
        if (hotel == null) {
            throw new InvalidReservationFlight("El hotel con el codigo " + request.getBooking().getHotelCode() + " no existe");
        }

        LocalDate dateFrom = request.getBooking().getDateFrom();//request.getDateFrom();
        LocalDate dateTo = request.getBooking().getDateTo();//getDateTo();


        if (hotel.getIsReserved()) {
            throw new InvalidReservationFlight(hotel.getName() + " el hotel ya fue reservado");
        }


        // noche de la doble  $6300")
        double pricePerNight = Double.parseDouble(hotel.getPricePerNight().replace("$", ""));
        long numberOfNights = dateFrom.until(dateTo).getDays();
        double amount = pricePerNight * numberOfNights;

        // Interes
        double interest = 5.5;
        double total = amount + (amount * interest / 100);


        //  respuesta
        BookingResponseDetailDTO bookingDetail = new BookingResponseDetailDTO();
        if(dateFrom.isAfter(dateTo) || dateTo.isBefore(dateFrom) ||
                (!dateFrom.isEqual(hotel.getDateFrom()) || !dateTo.isEqual(hotel.getDateTo()))){
            throw new InvalidDate("La fecha de llegada debe ser posterior a la fecha de salida " +
                    "o viceversa y además debe coincidir con las fechas disponibles del hotel");
        }

        bookingDetail.setDateFrom(dateFrom);
        bookingDetail.setDateTo(dateTo);


        if(!hotel.getDestination().equalsIgnoreCase(request.getBooking().getDestination()) ){
            throw new InvalidDate(hotel.getDestination() + " como destino es incorrecto");
        }

        bookingDetail.setDestination(request.getBooking().getDestination());

        bookingDetail.setHotelCode(request.getBooking().getHotelCode());
        if(request.getBooking().getPeopleAmount() > 5 ){
            throw new InvalidBookingHotel(hotel.getRoomType() + " No admite más de 5 personas ");
        }

        bookingDetail.setPeopleAmount(request.getBooking().getPeopleAmount());

        bookingDetail.setRoomType(request.getBooking().getRoomType());
        bookingDetail.setPeople(request.getBooking().getPeople());

        ResponseStatusDTO responseStatusDTO  = new ResponseStatusDTO();
        responseStatusDTO.setCode(201);
        responseStatusDTO.setMessage("El proceso termino satisfactoriamente");


        BookingResponseDTO response = new BookingResponseDTO();
        response.setUserName(request.getUserName());
        response.setAmount(amount);
        response.setInterest(interest);
        response.setTotal(total);
        response.setBooking(bookingDetail);
        response.setStatus(responseStatusDTO);

        // Marcar  reservada
        hotel.setIsReserved(true);
        hotelRepository.save(hotel);

        return response;
    }
}