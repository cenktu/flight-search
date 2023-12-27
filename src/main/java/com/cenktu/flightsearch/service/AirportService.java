package com.cenktu.flightsearch.service;

import com.cenktu.flightsearch.dao.AirportDAO;
import com.cenktu.flightsearch.model.Airport;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class AirportService {

    private AirportDAO airportDAO;

    public AirportService(AirportDAO airportDAO){
        this.airportDAO=airportDAO;
    }


    public List<Airport> getAllAirports() {
        return airportDAO.findAll();
    }

    public Optional<Airport> getSingleAirport(Long airportId) {
        return airportDAO.findById(airportId);
    }

    public Airport updateAirport(Long airportId, Airport updatedAirport) {
        Optional<Airport> airport = airportDAO.findById(airportId);
        if(airport.isPresent()){
            Airport presentAirport = airport.get();
            presentAirport.setCity(updatedAirport.getCity());
            airportDAO.save(presentAirport);
            return presentAirport;
        }
        return null;
    }

    public Airport createAirport(Airport newAirport) {
        return airportDAO.save(newAirport);
    }

    public void deleteAirport(Long airportId) {
        airportDAO.deleteById(airportId);
    }
}
