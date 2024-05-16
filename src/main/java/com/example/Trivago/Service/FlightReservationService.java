package com.example.Trivago.Service;

import com.example.Trivago.DTO.PaymentMethodDTO;
import com.example.Trivago.DTO.Request.FlightReservationRequestDTO;
import com.example.Trivago.DTO.Request.FlightReservationRequestDetailDTO;
import com.example.Trivago.DTO.Response.FlightReservationResponseDTO;
import com.example.Trivago.DTO.Response.FlightReservationResponseDetailDTO;
import com.example.Trivago.DTO.Response.ResponseStatusDTO;
import com.example.Trivago.Model.Flight;
import com.example.Trivago.Repository.IFlightRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;

@Service
public class FlightReservationService implements IFlightReservationService {

    @Autowired
    private IFlightRepository flightRepository;

    @Override
    public FlightReservationResponseDTO flightReservation(FlightReservationRequestDTO request) {
        LocalDate dateFrom = request.getFlightReservationDTO().getDateFrom();
        LocalDate dateTo = request.getFlightReservationDTO().getDateTo();

        // Encontrar el hotel por código
        Flight flight = flightRepository.getById(request.getFlightReservationDTO().getFlightNumber());
        if (flight == null) {
            throw new RuntimeException("Vuelo no encontrado");

        }


        double pricePerPerson = Double.parseDouble(flight.getPricePerPerson().replace("$", "").replace(",", ""));
        double amount = pricePerPerson * request.getFlightReservationDTO().getPeople().size();

        // Interes
        double interest = 5.5;
        double total = amount + (amount * interest / 100);

        FlightReservationResponseDetailDTO flightReservation = new FlightReservationResponseDetailDTO();
        flightReservation.setDateFrom(dateFrom);
        flightReservation.setDateTo(dateTo);
        flightReservation.setOrigin(request.getFlightReservationDTO().getOrigin());
        flightReservation.setDestination(request.getFlightReservationDTO().getDestination());
        flightReservation.setFlightNumber(request.getFlightReservationDTO().getFlightNumber());
        flightReservation.setSeats(request.getFlightReservationDTO().getSeats());
        flightReservation.setSeatType(request.getFlightReservationDTO().getSeatType());
        flightReservation.setPeople(request.getFlightReservationDTO().getPeople());

        PaymentMethodDTO paymentMethod = new PaymentMethodDTO();
        paymentMethod.setType(request.getFlightReservationDTO().getPaymentMethod().getType());
        paymentMethod.setNumberCard(request.getFlightReservationDTO().getPaymentMethod().getNumberCard());
        paymentMethod.setDues(request.getFlightReservationDTO().getPaymentMethod().getDues());
        flightReservation.setPaymentMethod(paymentMethod);

        ResponseStatusDTO status = new ResponseStatusDTO();
        status.setCode(201);
        status.setMessage("El proceso termino satisfactoriamente");

        FlightReservationResponseDTO response = new FlightReservationResponseDTO();
        response.setUserName(request.getUserName());
        response.setAmount(amount);
        response.setInterest(interest);
        response.setTotal(total);
        response.setFlightReservationDTO(flightReservation);
        response.setStatus(status);

        return response;

    }
}
