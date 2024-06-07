package com.example.Trivago.service;

import com.example.Trivago.DTO.FlightDTO;
import com.example.Trivago.DTO.Response.RespuestaDTO;
import com.example.Trivago.Exception.InvalidDate;
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
        assertNotNull(result);
        assertEquals(1, result.size());
        verify(flightRepository, times(1)).getAll();
    }

    @Test
    void testGetFlightByDate() {
        when(flightRepository.getAll()).thenReturn(flightList);
        List<FlightDTO> result = flightService.getFlightByDate(
                LocalDate.of(2025, 2, 10),
                LocalDate.of(2025, 2, 15),
                "Buenos Aires",
                "Puerto Iguazú"
        );
        assertNotNull(result);
        assertEquals(1, result.size());
    }

    @Test
    void testAddNewFlight() {
        FlightDTO flightDTO = new FlightDTO();
        flightDTO.setFlightNumber("BAPI-1235");
        flightDTO.setOrigin("Buenos Aires");
        flightDTO.setDestination("Puerto Iguazú");
        flightDTO.setDateFrom(LocalDate.of(2025, 2, 10));
        flightDTO.setDateTo(LocalDate.of(2025, 2, 15));

        when(flightRepository.save(any(Flight.class))).thenReturn(true);

        RespuestaDTO response = flightService.addNewFlight(flightDTO);

        assertNotNull(response);
        assertEquals("El vuelo ha sido creado con éxito", response.getMessage());

        verify(flightRepository, times(1)).save(any(Flight.class));
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
    }

    @Test
    void testUpdateFlight() {
        FlightDTO flightDTO = new FlightDTO();
        flightDTO.setFlightNumber("BAPI-1235");
        flightDTO.setOrigin("Buenos Aires");
        flightDTO.setDestination("Puerto Iguazú");
        flightDTO.setDateFrom(LocalDate.of(2024, 6, 1));
        flightDTO.setDateTo(LocalDate.of(2024, 6, 10));

        // Simula que el repositorio actualiza el vuelo y retorna true
        when(flightRepository.update(any(Flight.class))).thenReturn(true);

        RespuestaDTO response = flightService.updateFlight(flightDTO);
        assertNotNull(response);
        assertEquals("El vuelo ha sido actualizado con éxito", response.getMessage());
        verify(flightRepository, times(1)).update(any(Flight.class));
    }

    @Test
    void testDeleteFlightById() {
        String flightNumber = "BAPI-1235";

        // Simula que el repositorio elimina el vuelo y retorna true
        when(flightRepository.delete(flightNumber)).thenReturn(true);

        RespuestaDTO response = flightService.deleteFlightById(flightNumber);
        assertNotNull(response);
        assertEquals("El vuelo ha sido eliminado con exito", response.getMessage());
        verify(flightRepository, times(1)).delete(flightNumber);
    }
}
