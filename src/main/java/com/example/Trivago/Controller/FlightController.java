package com.example.Trivago.Controller;

import com.example.Trivago.Service.IFlight;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1")
public class FlightController {

    @Autowired
    IFlight flightService;


}
