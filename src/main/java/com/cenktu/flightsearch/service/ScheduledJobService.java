package com.cenktu.flightsearch.service;

import com.cenktu.flightsearch.dao.FlightDAO;
import com.cenktu.flightsearch.model.Airport;
import com.cenktu.flightsearch.model.Flight;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Service
@EnableScheduling
public class ScheduledJobService {

    private AirportService airportService;
    private FlightDAO flightDAO;

    public ScheduledJobService(AirportService airportService,FlightDAO flightDAO){
        this.airportService=airportService;
        this.flightDAO=flightDAO;
    }

    @Scheduled(cron = "0 0 12 * * ?")
    public void flightScheduler(){
        List<Flight> flightList= createFlight();

    }

    public List<Flight> createFlight(){
        List<Flight> flightList= new ArrayList<>();

        Airport departureAirport= new Airport();
        Airport arrivalAirport = new Airport();
        departureAirport.setCity("Trabzon");
        airportService.createAirport(departureAirport);
        arrivalAirport.setCity("Mersin");
        airportService.createAirport(arrivalAirport);

        Flight flight = new Flight();
        flight.setDepartureAirport(departureAirport);
        flight.setArrivalAirport(arrivalAirport);
        flight.setPrice(1000);
        flight.setDepartureTime(LocalDateTime.now().plusDays(1).plusHours(1));
        flight.setArrivalTime(LocalDateTime.now().plusDays(1).plusHours(3));
        flightList.add(flight);
        flightDAO.save(flight);

        Flight flight2 = new Flight();
        flight2.setDepartureAirport(arrivalAirport);
        flight2.setArrivalAirport(departureAirport);
        flight2.setPrice(1000);
        flight2.setDepartureTime(LocalDateTime.now().plusDays(2).plusHours(1));
        flight2.setArrivalTime(LocalDateTime.now().plusDays(2).plusHours(3));
        flightList.add(flight2);
        flightDAO.save(flight2);
        return flightList;
    }
}
