//package com.example.Trivago.Controller;
//
//import com.example.Trivago.DTO.TopClientDTO;
//import com.example.Trivago.Service.ICustomerService;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.http.ResponseEntity;
//import org.springframework.web.bind.annotation.GetMapping;
//import org.springframework.web.bind.annotation.RequestMapping;
//import org.springframework.web.bind.annotation.RequestParam;
//import org.springframework.web.bind.annotation.RestController;
//
//import java.util.HashMap;
//import java.util.List;
//import java.util.Map;
//
//@RestController
//@RequestMapping("/api/v1/top-customers")
//public class TopCustomerController {
//
//    @Autowired
//    private ICustomerService clientService;
//
//    @GetMapping("/clientsHotels/top-3")
//    public ResponseEntity<?> getTop3ClientsHotels(@RequestParam(value = "year") Integer year) {
//        if (year == null || String.valueOf(year).length() != 4) {
//            return ResponseEntity.badRequest().body("Please provide a valid 4-digit year.");
//        }
//
//        List<TopClientDTO> topClients = clientService.getTop3ClientsByYearHotels(year);
//        if (topClients.size() > 3) {
//            topClients = topClients.subList(0, 3);
//        }
//
//        Map<String, Object> response = new HashMap<>();
//        response.put("clients", topClients);
//
//        return ResponseEntity.ok(response);
//    }
//
//    @GetMapping("/clientsFlights/top-3")
//    public ResponseEntity<?> getTop3ClientsFlights(@RequestParam(value = "year") Integer year) {
//        if (year == null || String.valueOf(year).length() != 4) {
//            return ResponseEntity.badRequest().body("Please provide a valid 4-digit year.");
//        }
//
//        List<TopClientDTO> topClients = clientService.getTop3ClientsByYearFlights(year);
//        if (topClients.size() > 3) {
//            topClients = topClients.subList(0, 3);
//        }
//
//        Map<String, Object> response = new HashMap<>();
//        response.put("clients", topClients);
//
//        return ResponseEntity.ok(response);
//    }
//}
