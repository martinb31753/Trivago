package com.example.Trivago.Service;

public interface IncomeServiceInterface {
    Double getTotalIncome(String date);

    Double getTotalIncomeForMonth(int year, int month);
}
