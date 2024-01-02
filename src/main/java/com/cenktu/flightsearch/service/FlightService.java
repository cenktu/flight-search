package com.cenktu.flightsearch.service;

import com.cenktu.flightsearch.dao.FlightDAO;
import com.cenktu.flightsearch.model.Airport;
import com.cenktu.flightsearch.model.Flight;
import com.cenktu.flightsearch.request.FlightRequest;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class FlightService {


    private FlightDAO flightDAO;
    private AirportService airportService;
    public FlightService(FlightDAO flightDAO,AirportService airportService) {
        this.flightDAO=flightDAO;
        this.airportService=airportService;
    }


    public List<Flight> getAllFlights() {
        return flightDAO.findAll();
    }

    public Optional<Flight> getSingleFlight(Long flightId) {
        return flightDAO.findById(flightId);
    }

    public Flight createFlight(FlightRequest newFlightRequest) {
        Airport arrivalAirport =airportService.getSingleAirport(newFlightRequest.getArrivalAirportId());
        Airport departureAirport =airportService.getSingleAirport(newFlightRequest.getDepartureAirportId());
        if(arrivalAirport!=null && departureAirport!=null){
            Flight newFlight = new Flight();
            newFlight.setArrivalAirport(arrivalAirport);
            newFlight.setDepartureAirport(departureAirport);
            newFlight.setDepartureTime(newFlightRequest.getDepartureTime());
            newFlight.setArrivalTime(newFlightRequest.getArrivalTime());
            newFlight.setPrice(newFlightRequest.getPrice());
            return flightDAO.save(newFlight);
        }
        else{
            return null;
        }
    }

    public Flight updateFlight(Long flightId, FlightRequest updatedFlightRequest) {
        Optional<Flight> flight = flightDAO.findById(flightId);
        if(flight.isPresent()){
            Flight presentFlight = flight.get();
            presentFlight.setDepartureAirport(airportService.getSingleAirport(updatedFlightRequest.getDepartureAirportId()));
            presentFlight.setArrivalAirport(airportService.getSingleAirport(updatedFlightRequest.getArrivalAirportId()));
            presentFlight.setArrivalTime(updatedFlightRequest.getArrivalTime());
            presentFlight.setDepartureTime(updatedFlightRequest.getDepartureTime());
            presentFlight.setPrice(updatedFlightRequest.getPrice());
            flightDAO.save(presentFlight);
            return presentFlight;

        }
        return null;
    }

    public void deleteFlight(Long flightId) {
        flightDAO.deleteById(flightId);
    }

    public List<Flight> searchFlights(String departureCity, String arrivalCity, LocalDate departureDate, LocalDate returnDate) {
        List<Flight> desiredFlights = new ArrayList<>();
        LocalDateTime departureDateMin = departureDate.atTime(LocalTime.MIN);
        LocalDateTime departureDateMax = departureDate.atTime(LocalTime.MAX);
        if(returnDate!=null){
            LocalDateTime returnDateMin = returnDate.atTime(LocalTime.MIN);
            LocalDateTime returnDateMax = returnDate.atTime(LocalTime.MAX);
            desiredFlights.addAll(flightDAO.findByDepartureAirportCityAndArrivalAirportCityAndDepartureTimeBetween(departureCity,arrivalCity,departureDateMin,departureDateMax));
            desiredFlights.addAll(flightDAO.findByDepartureAirportCityAndArrivalAirportCityAndDepartureTimeBetween(arrivalCity,departureCity,returnDateMin,returnDateMax));
            return desiredFlights;
        }
        desiredFlights.addAll(flightDAO.findByDepartureAirportCityAndArrivalAirportCityAndDepartureTimeBetween(departureCity,arrivalCity,departureDateMin,departureDateMax));
        return desiredFlights;
    }
}
