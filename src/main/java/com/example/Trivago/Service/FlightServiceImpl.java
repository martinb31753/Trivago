package com.example.Trivago.Service;
import com.example.Trivago.DTO.FlightDTO;
import com.example.Trivago.DTO.Request.FlightReservationRequestDetailDTO;
import com.example.Trivago.DTO.Response.FlightReservationResponseDetailDTO;
import com.example.Trivago.Model.Flight;
import com.example.Trivago.Repository.IFlightRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;


@Service
public class FlightServiceImpl implements IFlight {
    @Autowired
    private IFlightRepository flightRepository;

    ModelMapper modelMapper = new ModelMapper();
    @Override
    public List<FlightDTO> getAll() {
        List<Flight> flightList = flightRepository.getAll();

        return flightList.stream()
                .map(flight -> modelMapper.map(flight, FlightDTO.class)).collect(java.util.stream.Collectors.toList());
    }

    @Override
    public FlightDTO getById(String flightCode) {
        return null;
    }
}
