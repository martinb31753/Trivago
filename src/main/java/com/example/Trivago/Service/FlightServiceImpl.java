package com.example.Trivago.Service;

import com.example.Trivago.DTO.FlightDTO;
import com.example.Trivago.DTO.Response.RespuestaDTO;
import com.example.Trivago.Entity.Flight;
import com.example.Trivago.Exception.FlightNotFound;
import com.example.Trivago.Exception.InvalidDate;
import com.example.Trivago.Exception.InvalidDestination;
import com.example.Trivago.Repository.IFlightRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class FlightServiceImpl implements IFlight {

    @Autowired
    private IFlightRepository flightRepository;

    private List<FlightDTO> flightList;
    private final ModelMapper modelMapper = new ModelMapper();

    public List<FlightDTO> getAll() {
        return flightRepository.getAllFlightsIsActive().stream()
                .map(flight -> modelMapper.map(flight, FlightDTO.class))
                .collect(Collectors.toList());
    }

    public List<FlightDTO> getFlightByDate(LocalDate dateFrom, LocalDate dateTo, String origin, String destination) {
        flightList = getAll();
        if (origin == null && destination == null && dateFrom == null && dateTo == null) {
            return flightList;
        }

        // Validar origen y destino
        if (origin != null && flightList.stream().noneMatch(flight -> flight.getOrigin().equalsIgnoreCase(origin))) {
            throw new InvalidDestination(origin + " no es un origen existente");
        }

        if (destination != null && flightList.stream().noneMatch(flight -> flight.getDestination().equalsIgnoreCase(destination))) {
            throw new InvalidDestination(destination + " no es un destino existente");
        }

        List<Flight> availableFlights = flightRepository.getFlightsAvailableFilter(dateFrom, dateTo, origin, destination);

        if (availableFlights.isEmpty()) {
            throw new InvalidDate("No hay vuelos disponibles para las fechas proporcionadas.");
        }

        return availableFlights.stream().map(flight -> modelMapper.map(flight, FlightDTO.class)).collect(Collectors.toList());
    }

    private boolean isWithinDateRange(LocalDate date, LocalDate rangeStart, LocalDate rangeEnd) {
        return !date.isBefore(rangeStart) && !date.isAfter(rangeEnd);
    }

    public RespuestaDTO addNewFlight(FlightDTO flightDTO) {
        if (flightDTO.getDateFrom().isAfter(flightDTO.getDateTo()) || flightDTO.getDateFrom().isEqual(flightDTO.getDateTo())) {
            throw new InvalidDate("La fecha de llegada debe ser posterior a la fecha de salida o viceversa, y además debe coincidir con las de fechas del vuelo");
        }

        Flight flight = modelMapper.map(flightDTO, Flight.class);
        flightRepository.save(flight);

        return new RespuestaDTO("El vuelo ha sido creado con éxito");
    }


    public RespuestaDTO updateFlight(FlightDTO updatedFlight, String flightNumber) {
        // Busca el vuelo por número de vuelo y tipo de asiento
        Optional<Flight> existingFlightObt = flightRepository.findByFlightNumberAndSeatType(
                flightNumber, updatedFlight.getSeatType());

        if (existingFlightObt.isPresent()) {
            Flight existingFlight = existingFlightObt.get();
            existingFlight.setOrigin(updatedFlight.getOrigin());
            existingFlight.setDestination(updatedFlight.getDestination());
            existingFlight.setPricePerPerson(updatedFlight.getPricePerPerson());
            existingFlight.setDateFrom(updatedFlight.getDateFrom());
            existingFlight.setDateTo(updatedFlight.getDateTo());

            flightRepository.save(existingFlight);
            return new RespuestaDTO("Vuelo modificado correctamente");
        } else {
            throw new FlightNotFound("No se encontró el vuelo ");
        }

    }


    public RespuestaDTO deleteFlightByCode(String flightCode) {
        Optional<Flight> optionalFlight = flightRepository.findByFlightNumber(flightCode);
        Long totalFlightReservation = flightRepository.countByFlightNumber(flightCode);
        if (optionalFlight.isPresent()) {
            if (totalFlightReservation > 0) {
                throw new FlightNotFound("No se puede eliminar el vuelo porque hay reservas realizadas");
            }
            Flight flight = optionalFlight.get();
            flight.setIsActive(false);
            flightRepository.save(flight);

            return new RespuestaDTO("El vuelo se eliminó con éxito");
        }
        return new RespuestaDTO("No se encontró el vuelo");
    }
    }





