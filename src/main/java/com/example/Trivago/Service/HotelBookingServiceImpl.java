package com.example.Trivago.Service;


import com.example.Trivago.DTO.Request.BookingRequestDTO;
import com.example.Trivago.DTO.Response.BookingResponseDTO;
import com.example.Trivago.DTO.Response.BookingResponseDetailDTO;
import com.example.Trivago.DTO.Response.ResponseStatusDTO;
import com.example.Trivago.Exception.InvalidBookingHotel;
import com.example.Trivago.Exception.InvalidDate;
import com.example.Trivago.Exception.InvalidDestination;
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
            throw new InvalidBookingHotel("El hotel con el codigo " + request.getBooking().getHotelCode() + " no existe");
        }

        LocalDate dateFrom = request.getBooking().getDateFrom();//request.getDateFrom();
        LocalDate dateTo = request.getBooking().getDateTo();//getDateTo();


        if (hotel.getIsReserved()) {
            throw new InvalidBookingHotel(hotel.getName() + " el hotel ya fue reservado");
        }


        // noche de la doble  $6300")
        double pricePerNight = Double.parseDouble(hotel.getPricePerNight().replace("$", ""));
        long numberOfNights = dateFrom.until(dateTo).getDays();
        double amount = pricePerNight * numberOfNights;

        // Interes
        double interest = 0.0;
        double total = amount;

        //Validacion!
        //Intereses
        //En caso que la tarjeta sea de crédito verificar recargo de intereses.
        // Ej: hasta 3 cuotas 5%, de 3 a 6 10%, etc.
        //En caso que sea tarjeta de débito verificar que no se incorporen intereses y que permita el pago en
        // una sola cuota,
        //Tarjeta de crédito: Devolver porcentaje y monto de interés (recargo).
        //Tarjeta de débito: Informar que se ha ingresado una cantidad de cuotas diferente a 1.

        if (request.getBooking().getPaymentMethod().getType().equalsIgnoreCase("CREDIT")) {
            if (request.getBooking().getPaymentMethod().getDues() <= 3) {
                interest = 5;
                total = amount + (amount * interest / 100);
            } else if (request.getBooking().getPaymentMethod().getDues() <= 6) {
                interest = 10;
                total = amount + (amount * interest / 100);
            } else if (request.getBooking().getPaymentMethod().getDues() <= 12) {
                interest = 15;
                total = amount + (amount * interest / 100);
            }
        }

        if (request.getBooking().getPaymentMethod().getType().equalsIgnoreCase("DEBIT") &&
                request.getBooking().getPaymentMethod().getDues() != 1) {
            throw new InvalidBookingHotel("La tarjeta de crédito solo acepta una cuota");
        }

        //  respuesta
        BookingResponseDetailDTO bookingDetail = new BookingResponseDetailDTO();
        if(dateFrom.isAfter(dateTo) ||
                !dateTo.isEqual(hotel.getDateTo()) ||
                !dateFrom.isEqual(hotel.getDateFrom())) {
            throw new InvalidDate("La fecha de llegada debe ser posterior a la fecha de salida " +
                    "o viceversa y además debe coincidir con las fechas disponibles del hotel");
        }

        bookingDetail.setDateFrom(dateFrom);
        bookingDetail.setDateTo(dateTo);

        if(!hotel.getDestination().equalsIgnoreCase(request.getBooking().getDestination())  ){
            throw new InvalidDestination(request.getBooking().getDestination() + " como destino es incorrecto");
        }

        bookingDetail.setDestination(request.getBooking().getDestination());

        bookingDetail.setHotelCode(request.getBooking().getHotelCode());
        if(request.getBooking().getPeopleAmount() > 5 ){
            throw new InvalidBookingHotel(hotel.getRoomType() + " No admite más de 5 personas ");
        }


        int maxCapacity = 0;
        switch (hotel.getRoomType().toLowerCase()) {
            case "single":
                maxCapacity = 1;
                break;
            case "double":
                maxCapacity = 2;
                break;
            case "triple":
                maxCapacity = 3;
                break;
            case "multiple":
                maxCapacity = 4;
                break;
            default:
                throw new InvalidBookingHotel("Tipo de habitación desconocido: " + hotel.getRoomType());
        }

        if (request.getBooking().getPeopleAmount() > maxCapacity) {
            throw new InvalidBookingHotel(hotel.getRoomType() + " no admite más de " + maxCapacity + " personas.");
        }


        bookingDetail.setPeopleAmount(request.getBooking().getPeopleAmount());

        switch (request.getBooking().getRoomType().toLowerCase()) {
            case "single":
                bookingDetail.setRoomType("Single");
                break;
            case "double":
                bookingDetail.setRoomType("Double");
                break;
            case "triple":
                bookingDetail.setRoomType("Triple");
                break;
            case "multiple":
                bookingDetail.setRoomType("Multiple");
                break;
            default:
                throw new InvalidBookingHotel("Tipo de habitación desconocido: " + request.getBooking().getRoomType());
        }


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