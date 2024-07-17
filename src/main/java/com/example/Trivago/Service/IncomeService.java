package com.example.Trivago.Service;


import com.example.Trivago.Repository.IFlightBookingRepository;
import com.example.Trivago.Repository.IHotelBookingRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

@Service

public class IncomeService implements IncomeServiceInterface {
    @Autowired
    private IHotelBookingRepository hotelBookingRepository;

    @Autowired
    private IFlightBookingRepository flightBookingRepository;


    @Override
    public Double getTotalIncome(String dateStr) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        LocalDate date = LocalDate.parse(dateStr, formatter);
        LocalDateTime startOfDay = date.atStartOfDay();
        LocalDateTime endOfDay = date.atTime(23, 59, 59);

        Double hotelIncome = hotelBookingRepository.sumAmountByDate(startOfDay, endOfDay);
        Double flightIncome = flightBookingRepository.sumAmountByDate(startOfDay, endOfDay);

        return (hotelIncome != null ? hotelIncome : 0.0) + (flightIncome != null ? flightIncome : 0.0);
    }

    @Override
    public Double getTotalIncomeForMonth(int year, int month) {
        LocalDate startDate = LocalDate.of(year, month, 1);
        LocalDate endDate = startDate.withDayOfMonth(startDate.lengthOfMonth());
        LocalDateTime startOfDay = startDate.atStartOfDay();
        LocalDateTime endOfDay = endDate.atTime(23, 59, 59);

        Double hotelIncome = hotelBookingRepository.sumAmountByDate(startOfDay, endOfDay);
        Double flightIncome = flightBookingRepository.sumAmountByDate(startOfDay, endOfDay);

        return (hotelIncome != null ? hotelIncome : 0.0) + (flightIncome != null ? flightIncome : 0.0);
    }
}
