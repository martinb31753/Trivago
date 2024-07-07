package com.example.Trivago.Service;

import com.example.Trivago.DTO.HotelDTO;
import com.example.Trivago.DTO.Response.RespuestaDTO;
import com.example.Trivago.Exception.InvalidDate;
import com.example.Trivago.Exception.InvalidDestination;
import com.example.Trivago.Entity.Hotel;
import com.example.Trivago.Repository.IHotelRepository;
import jakarta.transaction.Transactional;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class HotelServiceImpl implements IHotel {

    @Autowired
    private IHotelRepository hotelRepository;

    private List<HotelDTO> hotelList;
    ModelMapper modelMapper = new ModelMapper();


    public List<HotelDTO> getAll() {
        return hotelRepository.findAll()
                .stream()
                .map(hotel -> modelMapper.map(hotel, HotelDTO.class))
                .toList();
    }

    public List<HotelDTO> getAvailableHotels(LocalDate dateFrom, LocalDate dateTo, String destination) {
        hotelList = getAll();
        if (destination == null && dateFrom == null && dateTo == null) {
            return hotelList;
        }

        //validamos que el destino exista - validación - US0002
        if (destination != null && hotelList.stream().noneMatch(hotel -> hotel.getDestination().equalsIgnoreCase(destination))) {
            throw new InvalidDestination(destination + " no es un destino existente");
        }



        List<Hotel> availableHotels = hotelRepository.getHotelsAvailableFilter(dateFrom, dateTo, destination);
        if (availableHotels.isEmpty()) {
            throw new InvalidDate("No hay hoteles disponibles para las fechas proporcionadas.");
        }

        return availableHotels.stream().map(hotel -> modelMapper.map(hotel, HotelDTO.class)).collect(Collectors.toList());
    }



    private boolean isWithinDateRange(LocalDate date, LocalDate rangeStart, LocalDate rangeEnd) {
        return !date.isBefore(rangeStart) && !date.isAfter(rangeEnd);
    }

    public RespuestaDTO addNewHotel(HotelDTO newHotel) {

        Hotel hotel = new Hotel();

        modelMapper.map(newHotel, hotel);

        hotelRepository.save(hotel);

        return new RespuestaDTO("El hotel ha sido agregado con exito");

    }

    public RespuestaDTO updateHotelById(HotelDTO updateHotel) {

        Hotel hotel= new Hotel();

        modelMapper.map(updateHotel, hotel);

        hotelRepository.save(hotel);

        return new RespuestaDTO("El Hotel se actualizó con éxito");
    }

    @Override
    @Transactional
    public RespuestaDTO deleteHotelByCode(String hotelCode) {
        Optional<Hotel> optionalHotel = hotelRepository.getByHotelCode(hotelCode);
        if (optionalHotel.isPresent()) {
            Hotel hotel = optionalHotel.get();
            hotel.setIsActive(false);
            hotelRepository.save(hotel);
            return new RespuestaDTO("El Hotel se eliminó con exito");
        }
        return new RespuestaDTO("No se encontro el Hotel");
    }
}
