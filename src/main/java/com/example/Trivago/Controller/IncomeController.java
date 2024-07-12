package com.example.Trivago.Controller;

import com.example.Trivago.DTO.Response.IncomeDTO;
import com.example.Trivago.Service.IncomeServiceInterface;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("/api/v1")
@Validated
public class IncomeController {

    @Autowired
    IncomeServiceInterface incomeService;

    @GetMapping("/income")
    public IncomeDTO getIncome(@RequestParam String date) {
        Double totalIncome = incomeService.getTotalIncome(date);
        return new IncomeDTO(date, totalIncome);
    }

    @GetMapping("/income-month")
    public IncomeDTO getIncomeForMonth(@RequestParam int month, @RequestParam int year) {
        Double totalIncome = incomeService.getTotalIncomeForMonth(year, month);
        System.out.println("Total income CONTROLLER: " + totalIncome + " MONTH: " + month + " YEAR: " + year);
        String date = month + "-" + year;
        return new IncomeDTO(date, totalIncome);
    }
}
