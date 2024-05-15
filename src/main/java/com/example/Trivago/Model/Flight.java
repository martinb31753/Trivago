package com.example.Trivago.Model;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Flight {
    @JsonProperty("flight_number")
    private String flightNumber;
    private String origin;
    private String destination;
    @JsonProperty("seat_type")
    private String seatType;
    @JsonProperty("price_per_person")
    private String pricePerPerson;

    @JsonProperty("date_from")
    @JsonFormat(pattern = "dd-MM-yyyy")
    private LocalDate dateFrom;

    @JsonProperty("date_to")
    @JsonFormat(pattern = "dd-MM-yyyy")
    private LocalDate dateTo;

    public String getFlightNumber() {
        return flightNumber;
    }

    public void setFlightNumber(String flightNumber) {
        this.flightNumber = flightNumber;
    }

    public String getOrigin() {
        return origin;
    }

    public void setOrigin(String origin) {
        this.origin = origin;
    }

    public String getDestination() {
        return destination;
    }

    public void setDestination(String destination) {
        this.destination = destination;
    }

    public String getSeatType() {
        return seatType;
    }

    public void setSeatType(String seatType) {
        this.seatType = seatType;
    }

    public String getPricePerPerson() {
        return pricePerPerson;
    }

    public void setPricePerPerson(String pricePerPerson) {
        this.pricePerPerson = pricePerPerson;
    }

    public LocalDate getDateFrom() {
        return dateFrom;
    }

    public void setDateFrom(LocalDate dateFrom) {
        this.dateFrom = dateFrom;
    }

    public LocalDate getDateTo() {
        return dateTo;
    }

    public void setDateTo(LocalDate dateTo) {
        this.dateTo = dateTo;
    }
}
