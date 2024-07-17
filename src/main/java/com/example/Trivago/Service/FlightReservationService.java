package com.example.Trivago.Service;

import com.example.Trivago.DTO.FlightDTO;
import com.example.Trivago.DTO.PersonDTO;
import com.example.Trivago.DTO.Request.FlightReservationRequestDTO;
import com.example.Trivago.DTO.Response.FlightReservationResponseDTO;
import com.example.Trivago.DTO.Response.FlightReservationResponseDetailDTO;
import com.example.Trivago.DTO.Response.ResponseStatusDTO;
import com.example.Trivago.DTO.Response.RespuestaDTO;
import com.example.Trivago.Entity.FlightBooking;
import com.example.Trivago.Entity.People;
import com.example.Trivago.Exception.FlightNotFound;
import com.example.Trivago.Exception.InvalidBookingHotel;
import com.example.Trivago.Exception.InvalidDate;
import com.example.Trivago.Exception.InvalidReservationFlight;
import com.example.Trivago.Entity.Flight;
import com.example.Trivago.Repository.IPeopleRepository;
import com.example.Trivago.Repository.IFlightBookingRepository;
import com.example.Trivago.Repository.IFlightRepository;
import jakarta.persistence.EntityNotFoundException;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.*;
import java.util.stream.Collectors;

@Service
public class FlightReservationService implements IFlightReservationService {

    @Autowired
    private IFlightRepository flightRepository;

    @Autowired
    private IPeopleRepository peopleRepository;

    @Autowired
    private IFlightBookingRepository flightBookingRepository;

    private final ModelMapper modelMapper = new ModelMapper();
    HashMap<String, FlightReservationResponseDTO>  flightReserved = new HashMap<>();

    @Override
    public List<FlightReservationResponseDTO> findAll() {
        return flightBookingRepository.getFlightBookingsIsActive().stream()
                .map(flightReservation -> modelMapper.map(flightReservation, FlightReservationResponseDTO.class))
                .collect(Collectors.toList());
    }

    @Override
    public FlightReservationResponseDTO flightReservation(FlightReservationRequestDTO request) {

        if (flightReserved.containsKey(request.getFlightReservationDTO().getFlightNumber())) {
            throw new InvalidReservationFlight("El vuelo ya fue reservado");
        }

        LocalDate dateFrom = request.getFlightReservationDTO().getDateFrom();
        LocalDate dateTo = request.getFlightReservationDTO().getDateTo();

        // Encontrar el hotel por código //acá modifique el metodo del repo al que se llama
        Optional<Flight> flight = flightRepository.findByFlightNumber(request.getFlightReservationDTO().getFlightNumber());
        if (flight.isEmpty()) {
            throw new FlightNotFound(flight.get().getFlightNumber() + " vuelo no existe");
        }


        double pricePerPerson = Double.parseDouble(flight.get().getPricePerPerson().replace("$", "").replace(",", ""));

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

        if (request.getFlightReservationDTO().getPaymentMethod().getPaymentMethod().equalsIgnoreCase("CREDIT")) {
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

        if (request.getFlightReservationDTO().getPaymentMethod().getPaymentMethod().equalsIgnoreCase("DEBIT") &&
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
                (!dateFrom.isEqual(flight.get().getDateFrom()) || !dateTo.isEqual(flight.get().getDateTo()))) {
            throw new InvalidDate("La fecha de llegada debe ser posterior a la fecha de salida o viceversa, " +
                    "y además debe coincidir con las de fechas del vuelo");
        }

        flightReservation.setDateFrom(dateFrom);
        flightReservation.setDateTo(dateTo);

        if (!flight.get().getOrigin().equalsIgnoreCase(request.getFlightReservationDTO().getOrigin()) ||
                !flight.get().getDestination().equalsIgnoreCase(request.getFlightReservationDTO().getDestination())) {
            throw new InvalidDate("El origen y destino no coinciden con un vuelo existente");
        }

        flightReservation.setOrigin(request.getFlightReservationDTO().getOrigin());
        flightReservation.setDestination(request.getFlightReservationDTO().getDestination());
        flightReservation.setFlightNumber(request.getFlightReservationDTO().getFlightNumber());
        flightReservation.setSeats(request.getFlightReservationDTO().getSeats());

        if (!flight.get().getSeatType().equalsIgnoreCase(request.getFlightReservationDTO().getSeatType())) {
            throw new InvalidReservationFlight("El tipo de asiento no coincide con el tipo de asiento del vuelo");
        }
        flightReservation.setSeatType(request.getFlightReservationDTO().getSeatType());

        //esto estaba previamente y se elimina
//        flightReservation.setPeople(request.getFlightReservationDTO().getPeople());


        //Codigo pasado por Abru
//        // Verificar y manejar la lista de personas
//        List<PersonDTO> persDeReserva = request.getFlightReservationDTO().getPeople();
//        Set<People> persAGuardar = new HashSet<>();
//        for (PersonDTO peoples : persDeReserva) {
//            People person = modelMapper.map(peoples, People.class);
//            Optional<People> existingPerson = peopleRepository.findByName(person.getName());
//            if (existingPerson.isPresent()) {
//                person = existingPerson.get();
//            } else {
//                person = peopleRepository.save(person);
//            }
//            persAGuardar.add(person);
//        }
//
//
//
//        PaymentMethodDTO paymentMethodDTO = request.getFlightReservationDTO().getPaymentMethod();
//
//
////        // Verificar y manejar el cliente
////
////        Customer customer = request.getCustomer();
////        Optional<Customer> existingCustomer = customerRepository.findByEmail(customer.getEmail());
////        if (existingCustomer.isPresent()) {
////            customer = existingCustomer.get();
////        } else {
////            customer = customerRepository.save(customer);
////        }
////
////
//
//        // Crear y guardar la reserva de vuelo
//        FlightBooking flightBooking = new FlightBooking();
////        flightBooking.setSeats(flightDetails.getSeats());
//        flightBooking.setPaymentMethod(paymentMethodDTO.getType());
////
////        flightBooking.setCustomer(customer);
//        flightBooking.setPeople(persAGuardar);
////        flightBooking.setFlight(flight);
//
//        flightBookingRepository.save(flightBooking);


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

    @Override
    public RespuestaDTO updateFlightReservation(FlightReservationRequestDTO editReservation, Long id) {
        FlightBooking flightBookingEdit = flightBookingRepository.findById(id)
                .orElseThrow(() -> new InvalidReservationFlight("No se encontró la Reserva de vuelo"));
        // Actualizar campos simples
        flightBookingEdit.setSeats(editReservation.getFlightReservationDTO().getSeats());
        if (
                editReservation.getFlightReservationDTO().getSeats() != editReservation.getFlightReservationDTO().getPeople().size()){
            throw new InvalidReservationFlight("La cantidad de pasajeros no coincide con la cantidad de asientos "
                    + editReservation.getFlightReservationDTO().getSeats() + " contra " + editReservation.getFlightReservationDTO().getPeople().size());
        }

        flightBookingEdit.setPaymentMethod(editReservation.getFlightReservationDTO().getPaymentMethod().getPaymentMethod());
        flightBookingEdit.setNumberCard(editReservation.getFlightReservationDTO().getPaymentMethod().getNumberCard());
        flightBookingEdit.setDues(editReservation.getFlightReservationDTO().getPaymentMethod().getDues());

//        // Actualizar relación Customer
//        if (editReservation.getUserName() != null) {
//            Customer customer = peopleRepository.FindByUserName(editReservation.getUserName())
//                    .orElseThrow(() -> new EntityNotFoundException("Cliente no encontrado"));
//            flightBookingEdit.setCustomerId(customer.getId());
//        }

        // Actualizar relación People (personas)
        if (editReservation.getFlightReservationDTO().getPeople() != null && !editReservation.getFlightReservationDTO().getPeople().isEmpty()) {
            List<People> updatedPeople = peopleRepository.findAllPeopleBooking();
            for ( int i = 0; i < updatedPeople.size(); i++) {
                PersonDTO person = editReservation.getFlightReservationDTO().getPeople().get(i);
                updatedPeople.get(i).setDni(person.getDni());
                updatedPeople.get(i).setName(person.getName());
                updatedPeople.get(i).setLastName(person.getLastName());
                updatedPeople.get(i).setEmail(person.getEmail());

                peopleRepository.save(updatedPeople.get(i));
            }
        }

        // Actualizar relación Flight (vuelo)
        if (editReservation.getFlightReservationDTO().getFlightNumber() != null) {
            Flight flight = flightRepository.findByFlightNumber(editReservation.getFlightReservationDTO().getFlightNumber())
                    .orElseThrow(() -> new EntityNotFoundException("Vuelo no encontrado"));
            flightBookingEdit.setFlight(flight);
        }

        flightBookingRepository.save(flightBookingEdit);

        return new RespuestaDTO("Reserva de Hotel modificada correctamente");
    }


    public RespuestaDTO cancelFlight(Long id) {
        Optional<FlightBooking> optionalFlightReservation = flightBookingRepository.findById(id);
        if (optionalFlightReservation.isPresent()) {
            FlightBooking flightReservation = optionalFlightReservation.get();
            flightReservation.setIsActive(false);
            flightBookingRepository.save(flightReservation);

            return new RespuestaDTO("El vuelo ha sido cancelado con exito");
        }
        return new RespuestaDTO("No se encontro el vuelo");
    }
}