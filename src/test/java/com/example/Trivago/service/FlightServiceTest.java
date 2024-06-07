package com.example.Trivago.service;

import com.example.Trivago.DTO.FlightDTO;
import com.example.Trivago.DTO.Response.RespuestaDTO;
import com.example.Trivago.Exception.InvalidDate;
import com.example.Trivago.Exception.InvalidDestination;
import com.example.Trivago.Model.Flight;
import com.example.Trivago.Repository.IFlightRepository;
import com.example.Trivago.Service.FlightServiceImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.modelmapper.ModelMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class FlightServiceTest {

    private static final Logger log = LoggerFactory.getLogger(FlightServiceTest.class);
    @Mock
    private IFlightRepository flightRepository;

    @InjectMocks
    private FlightServiceImpl flightService;

    private ModelMapper modelMapper = new ModelMapper();

    private List<Flight> flightList;

    private List<FlightDTO> flightListDTO;

    @BeforeEach
    void setUp() {
        flightList = new ArrayList<>();
        Flight flight = new Flight();
        flight.setFlightNumber("BAPI-1235");
        flight.setOrigin("Buenos Aires");
        flight.setDestination("Puerto Iguazú");
        flight.setSeatType("Economy");
        flight.setPricePerPerson("$6,500");
        flight.setDateFrom(LocalDate.of(2025, 2, 10));
        flight.setDateTo(LocalDate.of(2025, 2, 15));
        flightList.add(flight);
    }

    @Test
    void testGetAllFlights() {
        when(flightRepository.getAll()).thenReturn(flightList);
        List<FlightDTO> result = flightService.getAll();
        flightListDTO = flightService.getAll();
        assertNotNull(result);
        assertEquals(flightListDTO, result);
     }

    @Test
    void testGetFlightByDate() {
        when(flightRepository.getAll()).thenReturn(flightList);
        flightListDTO = flightService.getAll();
        System.out.println(flightListDTO);
        List<FlightDTO> result = flightService.getFlightByDate(
                LocalDate.of(2025, 2, 10),
                LocalDate.of(2025, 2, 15),
                "Buenos Aires",
                "Puerto Iguazú"
        );
        assertNotNull(result);
        assertEquals(flightListDTO, result);
    }

    @Test
    void testAddNewFlightWithInvalidDate() {
        FlightDTO flightDTO = new FlightDTO();
        flightDTO.setFlightNumber("BAPI-1235");
        flightDTO.setOrigin("Buenos Aires");
        flightDTO.setDestination("Puerto Iguazú");
        flightDTO.setDateFrom(LocalDate.of(2024, 2, 10));
        flightDTO.setDateTo(LocalDate.of(2024, 2, 1));

        Exception exception = assertThrows(InvalidDate.class, () -> {
            flightService.addNewFlight(flightDTO);
        });

        String expectedMessage = "La fecha de llegada debe ser posterior a la fecha de salida o viceversa, y además debe coincidir con las de fechas del vuelo";
        String actualMessage = exception.getMessage();

        assertFalse(actualMessage.contains(expectedMessage));
        assertThrows(InvalidDate.class, () -> flightService.addNewFlight(flightDTO));
    }

    @Test
    void testGetFlightsByInvalidDate() {
        // Arrange
        LocalDate dateFrom = LocalDate.of(2024, 2, 10);
        LocalDate dateTo = LocalDate.of(2024, 2, 24);
        String expectedMessage = "No hay vuelos disponibles para las fechas proporcionadas.";

        // Act & Assert
        InvalidDate exception = assertThrows(InvalidDate.class, () -> {
            flightService.getFlightByDate(dateFrom, dateTo, null, null);
        });

        assertEquals(expectedMessage, exception.getMessage());
    }

    @Test
    void testGetFlightsByDateInvalidDestination() {
        // Arrange
        LocalDate dateFrom = LocalDate.of(2024, 2, 10);
        LocalDate dateTo = LocalDate.of(2024, 2, 24);
        String destination = "Buenos Aires";
        String expectedMessage = destination + " no es un destino existente";

        // Act & Assert
        InvalidDestination exception = assertThrows(InvalidDestination.class, () -> {
            flightService.getFlightByDate(dateFrom, dateTo, null, destination);
        });

        assertEquals(expectedMessage, exception.getMessage());
    }

    @Test
    void testGetFlightsByDateInvalidOrigin() {
        // Arrange
        LocalDate dateFrom = LocalDate.of(2024, 2, 10);
        LocalDate dateTo = LocalDate.of(2024, 2, 24);
        String origin = "Buenos Aires";
        String expectedMessage = origin + " no es un origen existente";

        // Act & Assert
        InvalidDestination exception = assertThrows(InvalidDestination.class, () -> {
            flightService.getFlightByDate(dateFrom, dateTo, origin, null);
        });

        assertEquals(expectedMessage, exception.getMessage());
    }

}
