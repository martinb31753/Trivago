package com.example.Trivago.Service;


import com.example.Trivago.DTO.PaymentMethodDTO;
import com.example.Trivago.DTO.Request.BookingRequestDTO;
import com.example.Trivago.DTO.Response.BookingResponseDTO;
import com.example.Trivago.DTO.Response.BookingResponseDetailDTO;
import com.example.Trivago.DTO.Response.ResponseStatusDTO;
import com.example.Trivago.Exception.InvalidReservation;
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

        Hotel hotel = hotelRepository.getById(request.getBooking().getHotelCode());

        if (hotel == null) {
            throw new InvalidReservation("Hotel no encontrado");
        }
        LocalDate dateFrom = request.getBooking().getDateFrom();//request.getDateFrom();
        LocalDate dateTo = request.getBooking().getDateTo();//getDateTo();


        if (hotel.getIs_reserved()) {
            throw new InvalidReservation("El hotel ya está reservado");
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

        BookingResponseDetailDTO bookingDetail = new BookingResponseDetailDTO();
        if(dateFrom.isAfter(dateTo) || dateTo.isBefore(dateFrom) ||
                (!dateFrom.isEqual(hotel.getDate_from()) || !dateTo.isEqual(hotel.getDate_to()))){
            throw new InvalidReservation("Las fechas son erroneas");
        }
        bookingDetail.setDateFrom(dateFrom);
        bookingDetail.setDateTo(dateTo);


        if(!hotel.getDestination().equalsIgnoreCase(request.getBooking().getDestination()) ){
            throw new InvalidReservation("El destino es incorrecto");
        }

        bookingDetail.setDestination(request.getBooking().getDestination());

        bookingDetail.setHotelCode(request.getBooking().getHotelCode());
        if(request.getBooking().getPeopleAmount() > 5 ){
            throw new InvalidReservation("No pueden ingresar más de 5 personas ");
        }
        bookingDetail.setPeopleAmount(request.getBooking().getPeopleAmount());


        bookingDetail.setRoomType(request.getBooking().getRoomType());
        bookingDetail.setPeople(request.getBooking().getPeople());


        PaymentMethodDTO paymentMethodDTO = new PaymentMethodDTO();

        if(!request.getBooking().getPaymentMethod().getType().equals("CREDIT")){
            throw new InvalidReservation("Su metodo de pago tiene que ser tarjeta de credito");
        }
        paymentMethodDTO.setType(request.getBooking().getPaymentMethod().getType());

        if (request.getBooking().getPaymentMethod().getNumberCard().length() < 16) {
            throw new InvalidReservation("tiene que tener por lo menos 16 dígitos");
        }

        paymentMethodDTO.setNumberCard(request.getBooking().getPaymentMethod().getNumberCard());



        if(request.getBooking().getPaymentMethod().getDues() < 1 ||
                request.getBooking().getPaymentMethod().getDues() > 12){
            throw new InvalidReservation("El valor de la cuota debe ser entre 1 y 12");
        }

        paymentMethodDTO.setDues(request.getBooking().getPaymentMethod().getDues());

        bookingDetail.setPaymentMethod(paymentMethodDTO);


        ResponseStatusDTO responseStatusDTO  = new ResponseStatusDTO();
        responseStatusDTO.setStatus(201);
        responseStatusDTO.setMessage("El proceso termino satisfactoriamente");




        BookingResponseDTO response = new BookingResponseDTO();
        response.setUserName(request.getUserName());
        response.setAmount(amount);
        response.setInterest(interest);
        response.setTotal(total);
        response.setBooking(bookingDetail);
        response.setStatus(responseStatusDTO);

        return response;

    }
}
