package com.cenktu.flightsearch.service;

import com.cenktu.flightsearch.dao.FlightDAO;
import com.cenktu.flightsearch.model.Flight;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class FlightService {


    private FlightDAO flightDAO;

    public FlightService(FlightDAO flightDAO) {
        this.flightDAO=flightDAO;
    }


    public List<Flight> getAllFlights() {
        return flightDAO.findAll();
    }

    public Optional<Flight> getSingleFlight(Long flightId) {
        return flightDAO.findById(flightId);
    }

    public Flight createFlight(Flight newFlight) {

        return flightDAO.save(newFlight);
    }

    public Flight updateFlight(Long flightId, Flight updatedFlight) {
        Optional<Flight> flight = flightDAO.findById(flightId);
        if(flight.isPresent()){
            Flight presentFlight = flight.get();
            presentFlight.setDepartureAirport(updatedFlight.getDepartureAirport());
            presentFlight.setArrivalAirport(updatedFlight.getArrivalAirport());
            presentFlight.setArrivalTime(updatedFlight.getArrivalTime());
            presentFlight.setDepartureTime(updatedFlight.getDepartureTime());
            presentFlight.setPrice(updatedFlight.getPrice());
            flightDAO.save(presentFlight);
            return presentFlight;

        }
        return null;
    }

    public void deleteFlight(Long flightId) {
        flightDAO.deleteById(flightId);
    }
}
