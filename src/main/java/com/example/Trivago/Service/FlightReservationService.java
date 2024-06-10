package com.example.Trivago.Service;

import com.example.Trivago.DTO.Request.FlightReservationRequestDTO;
import com.example.Trivago.DTO.Response.FlightReservationResponseDTO;
import com.example.Trivago.DTO.Response.FlightReservationResponseDetailDTO;
import com.example.Trivago.DTO.Response.ResponseStatusDTO;
import com.example.Trivago.Exception.FlightNotFound;
import com.example.Trivago.Exception.InvalidBookingHotel;
import com.example.Trivago.Exception.InvalidDate;
import com.example.Trivago.Exception.InvalidReservationFlight;
import com.example.Trivago.Model.Flight;
import com.example.Trivago.Repository.IFlightRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.HashMap;

@Service
public class FlightReservationService implements IFlightReservationService {

    @Autowired
    private IFlightRepository flightRepository;

    HashMap<String, FlightReservationResponseDTO>  flightReserved = new HashMap<>();

    @Override
    public FlightReservationResponseDTO flightReservation(FlightReservationRequestDTO request) {

        if (flightReserved.containsKey(request.getFlightReservationDTO().getFlightNumber())) {
            throw new InvalidReservationFlight("El vuelo ya fue reservado");
        }

        LocalDate dateFrom = request.getFlightReservationDTO().getDateFrom();
        LocalDate dateTo = request.getFlightReservationDTO().getDateTo();

        // Encontrar el hotel por código //acá modifique el metodo del repo al que se llama
        Flight flight = flightRepository.getByFlightNumber(request.getFlightReservationDTO().getFlightNumber());
        if (flight == null) {
            throw new FlightNotFound(flight.getFlightNumber() + " vuelo no existe");
        }


        double pricePerPerson = Double.parseDouble(flight.getPricePerPerson().replace("$", "").replace(",", ""));

        if (request.getFlightReservationDTO().getPeople().size() == 0) {
            throw new InvalidReservationFlight("No hay pasajeros existentes");
        }
        if (
                request.getFlightReservationDTO().getSeats() != request.getFlightReservationDTO().getPeople().size()){
            throw new InvalidReservationFlight("La cantidad de pasajeros no coincide con la cantidad de asientos "
                    + request.getFlightReservationDTO().getSeats() + " contra " + request.getFlightReservationDTO().getPeople().size());
        }

        double amount = pricePerPerson * request.getFlightReservationDTO().getPeople().size();

        // Interes
        double interest = 0.0;
        double total = amount;

        if (request.getFlightReservationDTO().getPaymentMethod().getType().equalsIgnoreCase("CREDIT")) {
            if (request.getFlightReservationDTO().getPaymentMethod().getDues() <= 3) {
                interest = 5;
                total = amount + (amount * interest / 100);
            } else if (request.getFlightReservationDTO().getPaymentMethod().getDues() <= 6) {
                interest = 10;
                total = amount + (amount * interest / 100);
            } else if (request.getFlightReservationDTO().getPaymentMethod().getDues() <= 12) {
                interest = 15;
                total = amount + (amount * interest / 100);
            }
        }

        if (request.getFlightReservationDTO().getPaymentMethod().getType().equalsIgnoreCase("DEBIT") &&
                request.getFlightReservationDTO().getPaymentMethod().getDues() != 1) {
            throw new InvalidBookingHotel("La tarjeta de crédito solo acepta una cuota");
        }

        //Validación para tipo de tarjeta - cuotas y % de interés-
        //En caso que la tarjeta sea de crédito verificar recargo de intereses.
        // Ej: hasta 3 cuotas 5%, de 3 a 6 10%, etc.
        // En caso que sea tarjeta de débito verificar que no se incorporen intereses y que permita
        // el pago en una sola cuota,
        // Tarjeta de crédito: Devolver porcentaje y monto de interés (recargo).
        // Tarjeta de débito: Informar que se ha ingresado una cantidad de cuotas diferente a 1.



        FlightReservationResponseDetailDTO flightReservation = new FlightReservationResponseDetailDTO();

        if (dateFrom.isAfter(dateTo) || dateTo.isBefore(dateFrom) ||
                (!dateFrom.isEqual(flight.getDateFrom()) || !dateTo.isEqual(flight.getDateTo()))) {
            throw new InvalidDate("La fecha de llegada debe ser posterior a la fecha de salida o viceversa, " +
                    "y además debe coincidir con las de fechas del vuelo");
        }

        flightReservation.setDateFrom(dateFrom);
        flightReservation.setDateTo(dateTo);

        if (!flight.getOrigin().equalsIgnoreCase(request.getFlightReservationDTO().getOrigin()) ||
                !flight.getDestination().equalsIgnoreCase(request.getFlightReservationDTO().getDestination())) {
            throw new InvalidDate("El origen y destino no coinciden con un vuelo existente");
        }

        flightReservation.setOrigin(request.getFlightReservationDTO().getOrigin());
        flightReservation.setDestination(request.getFlightReservationDTO().getDestination());
        flightReservation.setFlightNumber(request.getFlightReservationDTO().getFlightNumber());
        flightReservation.setSeats(request.getFlightReservationDTO().getSeats());

        if (!flight.getSeatType().equalsIgnoreCase(request.getFlightReservationDTO().getSeatType())) {
            throw new InvalidReservationFlight("El tipo de asiento no coincide con el tipo de asiento del vuelo");
        }
        flightReservation.setSeatType(request.getFlightReservationDTO().getSeatType());
        flightReservation.setPeople(request.getFlightReservationDTO().getPeople());


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

        flightReserved.put(request.getFlightReservationDTO().getFlightNumber(), response);

        for (FlightReservationResponseDTO flightReservationResponseDTO : flightReserved.values()) {
            System.out.println(flightReservationResponseDTO);
        }

        return response;
    }
}