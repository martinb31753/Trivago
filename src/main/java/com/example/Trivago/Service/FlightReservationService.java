package com.example.Trivago.Service;

import com.example.Trivago.DTO.PaymentMethodDTO;
import com.example.Trivago.DTO.Request.FlightReservationRequestDTO;
import com.example.Trivago.DTO.Response.FlightReservationResponseDTO;
import com.example.Trivago.DTO.Response.FlightReservationResponseDetailDTO;
import com.example.Trivago.DTO.Response.ResponseStatusDTO;
import com.example.Trivago.Exception.InvalidReservation;
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
            throw new InvalidReservation("El vuelo ya fue reservado");
        }

        LocalDate dateFrom = request.getFlightReservationDTO().getDateFrom();
        LocalDate dateTo = request.getFlightReservationDTO().getDateTo();

        // Encontrar el hotel por código //acá modifique el metodo del repo al que se llama
        Flight flight = flightRepository.getByFlightNumber(request.getFlightReservationDTO().getFlightNumber());
        if (flight == null) {
            throw new InvalidReservation(flight.getFlightNumber() + " vuelo no existe");
        }


        double pricePerPerson = Double.parseDouble(flight.getPricePerPerson().replace("$", "").replace(",", ""));

        if (request.getFlightReservationDTO().getPeople().size() == 0) {
            throw new InvalidReservation("No hay pasajeros existentes");
        }

        double amount = pricePerPerson * request.getFlightReservationDTO().getPeople().size();

        // Interes
        double interest = 5.5;
        double total = amount + (amount * interest / 100);

        FlightReservationResponseDetailDTO flightReservation = new FlightReservationResponseDetailDTO();

        if (dateFrom.isAfter(dateTo) || dateTo.isBefore(dateFrom) ||
                (!dateFrom.isEqual(flight.getDateFrom()) || !dateTo.isEqual(flight.getDateTo()))) {
            throw new InvalidReservation("La fecha de llegada debe ser posterior a la fecha de salida o viceversa, " +
                    "y además debe coincidir con las de fechas del vuelo");
        }

        flightReservation.setDateFrom(dateFrom);
        flightReservation.setDateTo(dateTo);

        if (!flight.getOrigin().equalsIgnoreCase(request.getFlightReservationDTO().getOrigin()) ||
                !flight.getDestination().equalsIgnoreCase(request.getFlightReservationDTO().getDestination())) {
            throw new InvalidReservation("El origen y destino no coinciden con un vuelo existente");
        }

        flightReservation.setOrigin(request.getFlightReservationDTO().getOrigin());
        flightReservation.setDestination(request.getFlightReservationDTO().getDestination());
        flightReservation.setFlightNumber(request.getFlightReservationDTO().getFlightNumber());
        flightReservation.setSeats(request.getFlightReservationDTO().getSeats());

        if (!flight.getSeatType().equalsIgnoreCase(request.getFlightReservationDTO().getSeatType())) {
            throw new InvalidReservation("El tipo de asiento no coincide con el tipo de asiento del vuelo");
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