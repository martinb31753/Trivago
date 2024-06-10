package com.example.Trivago.Service;

import com.example.Trivago.DTO.HotelDTO;
import com.example.Trivago.DTO.Response.RespuestaDTO;
import com.example.Trivago.Exception.InvalidDate;
import com.example.Trivago.Exception.InvalidDestination;
import com.example.Trivago.Model.Hotel;
import com.example.Trivago.Repository.IHotelRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

@Service
public class HotelServiceImpl implements IHotel {

    @Autowired
    private IHotelRepository hotelRepository;

    private List<HotelDTO> hotelList;
    ModelMapper modelMapper = new ModelMapper();


    @Override
    public List<HotelDTO> getAll() {
        return hotelRepository.getAll()
                .stream()
                .map(hotel -> modelMapper.map(hotel, HotelDTO.class))
                .toList();
    }

    @Override
    public List<HotelDTO> getAvailableHotels(LocalDate dateFrom, LocalDate dateTo, String destination) {
        hotelList = getAll();
        if (destination == null && dateFrom == null && dateTo == null) {
            return hotelList;
        }

        //validamos que el destino exista - validación - US0002
        if (destination != null && hotelList.stream().noneMatch(hotel -> hotel.getDestination().equalsIgnoreCase(destination))) {
            throw new InvalidDestination(destination + " no es un destino existente");
        }

        List<HotelDTO> availableHotels = hotelList.stream().filter(hotel ->
                (destination == null || hotel.getDestination().equalsIgnoreCase(destination)) &&
                        (dateFrom == null || isWithinDateRange(hotel.getDateFrom(), dateFrom, dateTo)) &&
                        (dateTo == null || isWithinDateRange(hotel.getDateTo(), dateFrom, dateTo)))
                .toList();
        if (availableHotels.isEmpty()) {
            throw new InvalidDate("No hay hoteles disponibles para las fechas proporcionadas.");
        }

        return availableHotels;
    }



    private boolean isWithinDateRange(LocalDate date, LocalDate rangeStart, LocalDate rangeEnd) {
        return !date.isBefore(rangeStart) && !date.isAfter(rangeEnd);
    }

    @Override
    public RespuestaDTO addNewHotel(HotelDTO newHotel) {

        Hotel hotel = new Hotel();

        modelMapper.map(newHotel, hotel);

        hotelRepository.save(hotel);

        return new RespuestaDTO("El hotel ha sido agregado con exito");

    }

    @Override
    public RespuestaDTO updateHotelById(HotelDTO updateHotel) {

        Hotel hotel= new Hotel();

        modelMapper.map(updateHotel, hotel);

        hotelRepository.update(hotel);

        return new RespuestaDTO("El Hotel se actualizó con éxito");
    }

    @Override
    public RespuestaDTO deleteHotelById(String hotelCode) {

        hotelRepository.delete(hotelCode);

        return new RespuestaDTO ("El Hotel se eliminó con éxito");
    }
}
