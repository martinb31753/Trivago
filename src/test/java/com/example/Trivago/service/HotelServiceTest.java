package com.example.Trivago.service;

import com.example.Trivago.DTO.HotelDTO;
import com.example.Trivago.Exception.InvalidDate;
import com.example.Trivago.Exception.InvalidDestination;
import com.example.Trivago.Model.Hotel;
import com.example.Trivago.Repository.HotelRepositoryImpl;
import com.example.Trivago.Service.HotelServiceImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.modelmapper.ModelMapper;

import java.time.LocalDate;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)

public class HotelServiceTest {

    @Mock
    private HotelRepositoryImpl hotelRepository;

    @Mock
    private ModelMapper modelMapper;

    @InjectMocks
    private HotelServiceImpl hotelService;

    private List<Hotel> hotels;
    private List<HotelDTO> hotelsDTO;

    @BeforeEach
    public void setup() {
        hotels = createHotelList();
        hotelsDTO = createHotelDTOList();
        when(hotelRepository.getAll()).thenReturn(hotels);
        when(modelMapper.map(hotels.get(0), HotelDTO.class)).thenReturn(hotelsDTO.get(0));
        when(modelMapper.map(hotels.get(1), HotelDTO.class)).thenReturn(hotelsDTO.get(1));
        when(modelMapper.map(hotels.get(2), HotelDTO.class)).thenReturn(hotelsDTO.get(2));
        when(modelMapper.map(hotels.get(3), HotelDTO.class)).thenReturn(hotelsDTO.get(3));
    }

    private List<Hotel> createHotelList() {
        Hotel hotel1 = new Hotel("CH-0002",
                "Cataratas Hotel",
                "Puerto Iguazú",
                "Double",
                "$6300",
                LocalDate.of(2025, 2, 10),
                LocalDate.of(2025, 3, 20),
                false);

        Hotel hotel2 = new Hotel("CH-0003",
                "Cataratas Hotel 2",
                "Puerto Iguazú",
                "Triple",
                "$8200",
                LocalDate.of(2025, 2, 10),
                LocalDate.of(2025, 3, 23),
                false);

        Hotel hotel3 = new Hotel("CH-0004",
                "Cataratas Hotel 3",
                "Puerto Iguazú",
                "Triple",
                "$8200",
                LocalDate.of(2025, 2, 10),
                LocalDate.of(2025, 3, 23),
                false);

        Hotel hotel4 = new Hotel("CH-0005",
                "Cataratas Hotel 4",
                "Puerto Iguazú",
                "Triple",
                "$8200",
                LocalDate.of(2025, 2, 10),
                LocalDate.of(2025, 3, 23),
                false);

        return List.of(hotel1, hotel2, hotel3, hotel4);
    }

    private List<HotelDTO> createHotelDTOList() {
        HotelDTO hotelDTO1 = new HotelDTO("CH-0002",
                "Cataratas Hotel",
                "Puerto Iguazú",
                "Double",
                "$6300",
                LocalDate.of(2025, 2, 10),
                LocalDate.of(2025, 3, 20),
                false);

        HotelDTO hotelDTO2 = new HotelDTO("CH-0003",
                "Cataratas Hotel 2",
                "Puerto Iguazú",
                "Triple",
                "$8200",
                LocalDate.of(2025, 2, 10),
                LocalDate.of(2025, 3, 23),
                false);

        HotelDTO hotelDTO3 = new HotelDTO("CH-0004",
                "Cataratas Hotel 3",
                "Puerto Iguazú",
                "Triple",
                "$8200",
                LocalDate.of(2025, 2, 10),
                LocalDate.of(2025, 3, 23),
                false);

        HotelDTO hotelDTO4 = new HotelDTO("CH-0005",
                "Cataratas Hotel 4",
                "Puerto Iguazú",
                "Triple",
                "$8200",
                LocalDate.of(2025, 2, 10),
                LocalDate.of(2025, 3, 23),
                false);

        return List.of(hotelDTO1, hotelDTO2, hotelDTO3, hotelDTO4);
    }



    @Test
    public void testGetAll() {
        when(hotelRepository.getAll()).thenReturn(hotels);

        List<HotelDTO> result = hotelService.getAll();

        assertEquals(hotelsDTO, result, "La lista de hoteles no coincide con la esperada");
    }

    @Test
    public void testGetAvailableHotelsByDate() {

        LocalDate dateFrom = LocalDate.of(2025, 2, 10);
        LocalDate dateTo = LocalDate.of(2025, 3, 20);
        String destination = "Puerto Iguazú";

        List<HotelDTO> hotelsDTOFiltrados = hotelService.getAvailableHotels(dateFrom, dateTo, destination);

        List<HotelDTO> result = hotelService.getAvailableHotels(dateFrom, dateTo, destination);

        for (HotelDTO hotelsDTODTO : result) {
            System.out.println(hotelsDTODTO);
        }

        assertEquals(hotelsDTOFiltrados, result, "La lista de hoteles no coincide con la esperada");

    }

    @Test
    public void testGetAvailableHotelsFail() {
        LocalDate dateFrom = LocalDate.of(2025, 2, 11);
        LocalDate dateTo = LocalDate.of(2025, 3, 20);
        String destination = "Puerto Iguazú";

        String expectedErrorMessage = "No hay hoteles disponibles para las fechas proporcionadas.";

        assertEquals(expectedErrorMessage, assertThrows(RuntimeException.class, () -> {
            List<HotelDTO> result = hotelService.getAvailableHotels(dateFrom, dateTo, destination);
        }).getMessage());

        assertThrows(InvalidDate.class, () -> hotelService.getAvailableHotels(dateFrom, dateTo, destination));
    }

    @Test
    public void testGetAvailableHotelsDestinationFail() {
        LocalDate dateFrom = LocalDate.of(2025, 2, 10);
        LocalDate dateTo = LocalDate.of(2025, 3, 20);
        String destination = "America";

        String expectedErrorMessage = destination+" no es un destino existente";

        assertEquals(expectedErrorMessage, assertThrows(RuntimeException.class, () -> {
            List<HotelDTO> result = hotelService.getAvailableHotels(dateFrom, dateTo, destination);
        }).getMessage());

        assertThrows(InvalidDestination.class, () -> hotelService.getAvailableHotels(dateFrom, dateTo, destination));
    }

}
