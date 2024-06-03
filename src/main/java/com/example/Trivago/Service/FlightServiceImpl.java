package com.example.Trivago.Service;
import com.example.Trivago.DTO.FlightDTO;
import com.example.Trivago.DTO.Response.RespuestaDTO;
import com.example.Trivago.Exception.InvalidDate;
import com.example.Trivago.Model.Flight;
import com.example.Trivago.Repository.IFlightRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;


@Service
public class FlightServiceImpl implements IFlight {
    @Autowired
    private IFlightRepository flightRepository;

    private List<FlightDTO> flightList;
    ModelMapper modelMapper = new ModelMapper();

    @Override
    public List<FlightDTO> getAll() {
        return flightRepository.getAll().stream()
                .map(flight -> modelMapper.map(flight, FlightDTO.class)).toList();
    }

    @Override
    public List<FlightDTO> getFlightByDate(LocalDate date_from, LocalDate date_to, String origin, String destination) {
        flightList = getAll();
        if (origin == null && destination == null && date_from == null && date_to == null) {
            return flightList;
        }
        return flightList.stream()
                .filter(flight ->
                        (origin == null || flight.getOrigin().equalsIgnoreCase(origin)) &&
                                (destination == null || flight.getDestination().equalsIgnoreCase(destination)) &&
                                (date_from == null || isWithinDateRange(flight.getDateFrom(), date_from, date_to)) &&
                                (date_to == null || isWithinDateRange(flight.getDateTo(), date_from, date_to))
                )
                .toList();
    }

    private boolean isWithinDateRange(LocalDate date, LocalDate rangeStart, LocalDate rangeEnd) {
        return !date.isBefore(rangeStart) && !date.isAfter(rangeEnd);
    }


    @Override
    public FlightDTO getByFlightNumber(String flightCode) {
        return null;
    }

    @Override
    public RespuestaDTO addNewFlight(FlightDTO flightDTO) {
        Flight flight = new Flight();

        if (flightDTO.getDateFrom().isAfter(flightDTO.getDateTo()) || flightDTO.getDateTo().isBefore(flightDTO.getDateFrom()) ||
                (!flightDTO.getDateFrom().isEqual(flightDTO.getDateTo()) || !flightDTO.getDateTo().isEqual(flightDTO.getDateFrom()))) {
            throw new InvalidDate("La fecha de llegada debe ser posterior a la fecha de salida o viceversa, " +
                    "y además debe coincidir con las de fechas del vuelo");
        }
        modelMapper.map(flightDTO, flight);

        flightRepository.save(flight);

        return new RespuestaDTO("El vuelo ha sido creado con éxito");
    }


    @Override
    public RespuestaDTO updateFlight(FlightDTO updateFlight) {
        Flight flight = new Flight();

        modelMapper.map(updateFlight, flight);

        flightRepository.update(flight);

        return new RespuestaDTO("El vuelo ha sido actualizado con éxito");
    }

    @Override
    public RespuestaDTO deleteFlightById(String flightNumber) {

        flightRepository.delete(flightNumber);

        return new RespuestaDTO ("El vuelo ha sido eliminado con exito");
    }

}

