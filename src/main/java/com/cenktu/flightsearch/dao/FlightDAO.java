package com.cenktu.flightsearch.dao;

import com.cenktu.flightsearch.model.Airport;
import com.cenktu.flightsearch.model.Flight;
import org.springframework.data.repository.ListCrudRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.List;

@Repository
public interface FlightDAO extends ListCrudRepository<Flight,Long> {
    List<Flight> findByDepartureAirportCityAndArrivalAirportCityAndDepartureTimeBetween(String departureCity, String arrivalCity, LocalDateTime departureDateMin, LocalDateTime departureDateMax);
    //List<Flight> findByDepartureAirportCityAndArrivalAirportCityAndDepartureTimeAndArrivalTime(String departureCity, String arrivalCity, LocalDateTime departureTime, LocalDateTime arrivalTime);
}
