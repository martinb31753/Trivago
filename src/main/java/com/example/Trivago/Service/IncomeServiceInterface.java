package com.example.Trivago.Service;

import org.springframework.stereotype.Service;

import java.time.LocalDate;

public interface IncomeServiceInterface {
    Double getTotalIncome(String date);

    Double getTotalIncomeForMonth(int year, int month);
}
