package com.example.Trivago.service;

import com.example.Trivago.DTO.PaymentMethodDTO;
import com.example.Trivago.DTO.PersonDTO;
import com.example.Trivago.DTO.Request.FlightReservationRequestDTO;
import com.example.Trivago.DTO.Request.FlightReservationRequestDetailDTO;
import com.example.Trivago.DTO.Response.FlightReservationResponseDTO;
import com.example.Trivago.DTO.Response.FlightReservationResponseDetailDTO;
import com.example.Trivago.DTO.Response.ResponseStatusDTO;
import com.example.Trivago.Exception.FlightNotFound;
import com.example.Trivago.Exception.InvalidDate;
import com.example.Trivago.Exception.InvalidReservationFlight;
import com.example.Trivago.Model.Flight;
import com.example.Trivago.Repository.IFlightRepository;
import com.example.Trivago.Service.FlightReservationService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class FlightReservationServiceTest {

    @Mock
    private IFlightRepository flightRepository;

    @InjectMocks
    private FlightReservationService flightReservationService;

    private FlightReservationRequestDTO requestDTO;
    private Flight flight;

    @BeforeEach
    void setUp() {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy");

        flight = new Flight();
        flight.setFlightNumber("BAPI-1235");
        flight.setPricePerPerson("$6,500");
        flight.setDateFrom(LocalDate.parse("10-02-2025", formatter));
        flight.setDateTo(LocalDate.parse("15-02-2025", formatter));
        flight.setOrigin("Buenos Aires");
        flight.setDestination("Puerto Iguazú");
        flight.setSeatType("Economy");

        PersonDTO person1 = new PersonDTO();
        person1.setDni("12345678");
        person1.setName("Juan");
        person1.setLastName("Perez");
        person1.setBirthDate(LocalDate.parse("10-11-1982", formatter));
        person1.setEmail("juanperez@gmail.com");

        PersonDTO person2 = new PersonDTO();

        person2.setDni("87654321");
        person2.setName("Maria");
        person2.setLastName("Lopez");
        person2.setBirthDate(LocalDate.parse("01-05-1985", formatter));
        person2.setEmail("marialopez@gmail.com");

        FlightReservationRequestDetailDTO flightReservationDTO = new FlightReservationRequestDetailDTO();
        flightReservationDTO.setFlightNumber("BAPI-1235");
        flightReservationDTO.setDateFrom(LocalDate.parse("10-02-2025", formatter));
        flightReservationDTO.setDateTo(LocalDate.parse("15-02-2025", formatter));
        flightReservationDTO.setOrigin("Buenos Aires");
        flightReservationDTO.setDestination("Puerto Iguazú");
        flightReservationDTO.setSeats(2);
        flightReservationDTO.setSeatType("Economy");
        flightReservationDTO.setPeople(Arrays.asList(person1, person2));
        flightReservationDTO.setPaymentMethod(new PaymentMethodDTO("CREDIT", "1234-1234-1234-1234", 3));

        requestDTO = new FlightReservationRequestDTO();
        requestDTO.setUserName("juanperez@gmail.com");
        requestDTO.setFlightReservationDTO(flightReservationDTO);

    }

    @Test
    void testFlightReservation_Success() {
        FlightReservationResponseDTO flightReservationResponseDTO = new FlightReservationResponseDTO();
        flightReservationResponseDTO.setUserName("juanperez@gmail.com");
        flightReservationResponseDTO.setAmount(13000.0);
        flightReservationResponseDTO.setInterest(5.0);
        flightReservationResponseDTO.setTotal(13650.0);
        flightReservationResponseDTO.setFlightReservationDTO(new FlightReservationResponseDetailDTO(
                LocalDate.of(2025, 02, 10),
                LocalDate.of(2025, 02, 15),
                "Buenos Aires",
                "Puerto Iguazú",
                "BAPI-1235",
                2,
                "Economy",
                List.of(
                        new PersonDTO("12345678", "Juan", "Perez", LocalDate.parse("10-11-1982", DateTimeFormatter.ofPattern("dd-MM-yyyy")), "juanperez@gmail.com"),
                        new PersonDTO("87654321", "Maria", "Lopez", LocalDate.parse("01-05-1985", DateTimeFormatter.ofPattern("dd-MM-yyyy")), "marialopez@gmail.com")
                )
        ));
        flightReservationResponseDTO.setStatus(new ResponseStatusDTO(201, "El proceso termino satisfactoriamente"));
        when(flightRepository.getByFlightNumber("BAPI-1235")).thenReturn(flight);

        FlightReservationResponseDTO response = flightReservationService.flightReservation(requestDTO);

        assertNotNull(response);
        assertEquals(flightReservationResponseDTO, response);
    }

    @Test
    void testFlightReservation_InvalidReservationFlight() {
        when(flightRepository.getByFlightNumber("BAPI-1235")).thenReturn(flight);

        flightReservationService.flightReservation(requestDTO);

        assertThrows(InvalidReservationFlight.class, () -> flightReservationService.flightReservation(requestDTO));
    }
}
