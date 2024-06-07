package com.example.Trivago.service;

import com.example.Trivago.DTO.HotelDTO;
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

        return List.of(hotel1, hotel2);
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

        return List.of(hotelDTO1, hotelDTO2);
    }


    @Test
    public void testGetAll() {
        when(hotelRepository.getAll()).thenReturn(hotels);

        List<HotelDTO> result = hotelService.getAll();

        assertEquals(hotels.size(), result.size(), "The number of hotels returned is not as expected");
    }

    @Test
    public void testGetAvailableHotels() {
        List<HotelDTO> result = hotelService.getAvailableHotels(LocalDate.of(2025, 2, 10), LocalDate.of(2025, 3, 20), null);


//        assertTrue(result.contains(hotelsDTO.get(0)));

        verify(hotelRepository, times(1)).getAll();
    }

    @Test
    public void testGetAvailableHotelsII() {
        LocalDate dateFrom = LocalDate.of(2025, 2, 10);
        LocalDate dateTo = LocalDate.of(2025, 3, 20);
        String destination = "Puerto Iguazú";

        List<HotelDTO> result = hotelService.getAvailableHotels(dateFrom, dateTo, destination);

        System.out.println(result);
        // Verificar que las fechas de los hoteles en los resultados coinciden con las fechas ingresadas
        for (HotelDTO hotelDTO : result) {
            assertEquals(dateFrom, hotelDTO.getDateFrom(), "La fecha de inicio del hotel no coincide con la fecha de inicio ingresada");
            assertEquals(dateTo, hotelDTO.getDateTo(), "La fecha de fin del hotel no coincide con la fecha de fin ingresada");
        }

        verify(hotelRepository, times(1)).getAll();
    }






}
