package com.cenktu.flightsearch.controller;

import com.cenktu.flightsearch.model.Airport;
import com.cenktu.flightsearch.service.AirportService;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/airport")
public class AirportController {

    private AirportService airportService;

    public AirportController(AirportService airportService){
        this.airportService=airportService;
    }

    @GetMapping
    public List<Airport> getAllAirports(){
        return airportService.getAllAirports();
    }

    @GetMapping("/{airportId}")
    public Optional<Airport> getSingleAirport(@PathVariable Long airportId){
        return airportService.getSingleAirport(airportId);
    }


    @PostMapping
    public Airport createAirport(@RequestBody Airport newAirport){
        return airportService.createAirport(newAirport);
    }

    @PutMapping("/{airportId}")
    public Airport updateAirport(@PathVariable Long airportId,@RequestBody Airport updatedAirport){
        return airportService.updateAirport(airportId,updatedAirport);
    }

    @DeleteMapping("/{airportId}")
    public void deleteAirport(@PathVariable Long airportId){
        airportService.deleteAirport(airportId);
    }

}
