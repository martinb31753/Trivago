//package com.example.Trivago.Service;
//
//import com.example.Trivago.DTO.TopClientDTO;
//import com.example.Trivago.Repository.ICustomerRepository;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.stereotype.Service;
//
//import java.util.List;
//import java.util.stream.Collectors;
//
//@Service
//public class CustomerServiceImpl implements ICustomerService {
//
//    @Autowired
//    private ICustomerRepository customerRepository;
//
//    public List<TopClientDTO> getTop3ClientsByYearHotels(int year) {
//        return customerRepository.findTop3ClientsByYearHotels(year).stream()
//                .limit(3)
//                .collect(Collectors.toList());
//    }
//
//    public List<TopClientDTO> getTop3ClientsByYearFlights(int year) {
//        return customerRepository.findTop3ClientsByYearFlights(year).stream()
//                .limit(3)
//                .collect(Collectors.toList());
//    }
//}
