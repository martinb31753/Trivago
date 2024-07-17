package com.example.Trivago.Config;

import com.example.Trivago.DTO.AllHotelReservationsDTO;
import com.example.Trivago.DTO.PersonDTO;
import com.example.Trivago.Entity.HotelBooking;
import com.example.Trivago.Entity.People;
import org.modelmapper.ModelMapper;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ModelMapperConfig {
    @Bean
    public ModelMapper modelMapper() {
        ModelMapper modelMapper = new ModelMapper();

        // Configuración específica de mapeo para PersonDTO y People
        modelMapper.typeMap(PersonDTO.class, People.class);

//        // Configuración específica de mapeo para HotelBooking y AllHotelReservationsDTO
//        modelMapper.createTypeMap(HotelBooking.class, AllHotelReservationsDTO.class)
//                .addMappings(mapper -> {
//                    mapper.map(HotelBooking::getPeople, AllHotelReservationsDTO::setHotelReservations);
//                });

        return modelMapper;
    }
}
