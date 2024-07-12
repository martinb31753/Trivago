package com.example.Trivago.Controller;

import com.example.Trivago.DTO.HotelDTO;
import com.example.Trivago.Service.IHotel;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;


@RestController
@RequestMapping("/api/v1/hotels")
@Validated
public class HotelController {

    @Autowired
    IHotel hotelService;

    @GetMapping()
    public ResponseEntity<?> getAvailableHotels(
            @RequestParam(value="date_from", required = false) @DateTimeFormat(pattern="dd-MM-yyyy")  LocalDate date_from,
            @RequestParam(value = "date_to", required = false) @DateTimeFormat(pattern = "dd-MM-yyyy") LocalDate date_to,
            @RequestParam(value = "destination", required = false) String destination) {
        List<HotelDTO> filteredHotels = hotelService.getAvailableHotels(date_from, date_to, destination);
        return ResponseEntity.ok(filteredHotels);
    }
    @PostMapping("/new")
    public ResponseEntity<?> addNewHotel(@RequestBody HotelDTO newHotel) {
        return new ResponseEntity<>( hotelService.addNewHotel(newHotel),HttpStatus.CREATED);
    }


    @PutMapping("/update-hotel/{hotelCode}")
    public ResponseEntity<?> updateHotelById(@RequestBody HotelDTO updateHotel, @PathVariable String hotelCode) {
        return new ResponseEntity<>(hotelService.updateHotelById(updateHotel), HttpStatus.OK);

    }

    @PutMapping("/delete/{hotelCode}")
    public ResponseEntity<?> deleteHotel(@PathVariable("hotelCode") String hotelCode) {
        hotelService.deleteHotelByCode(hotelCode);
        return ResponseEntity.ok("Hotele No. " + hotelCode + " eliminado");
    }
}