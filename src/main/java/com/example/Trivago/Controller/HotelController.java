package com.example.Trivago.Controller;

import com.example.Trivago.DTO.HotelDTO;
import com.example.Trivago.DTO.Response.ResponseStatusDTO;
import com.example.Trivago.Model.Hotel;
import com.example.Trivago.Service.IHotel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/v1")
public class HotelController {

    @Autowired
    IHotel hotelService;

    @GetMapping("/hotels")
    public ResponseEntity<?> getAllHotels() {
        List<HotelDTO> hotels = hotelService.getAll();
        return new ResponseEntity<>(hotels, HttpStatus.OK);
    }

}