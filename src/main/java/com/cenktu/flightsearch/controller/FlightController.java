package com.cenktu.flightsearch.controller;

import com.cenktu.flightsearch.model.Flight;
import com.cenktu.flightsearch.service.FlightService;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/flight")
public class FlightController {

    private FlightService flightService ;
    public FlightController(FlightService flightService) {
        this.flightService =flightService;
    }
    @GetMapping
    public List<Flight> getAllFlights(){
        return flightService.getAllFlights();
    }

    @GetMapping ("/{flightId}")
    public Optional<Flight> getSingleFlight(@PathVariable Long flightId){
        return flightService.getSingleFlight(flightId);
    }

    @PostMapping
    public Flight createFlight(@RequestBody Flight newFlight){
        return flightService.createFlight(newFlight);
    }

    @PutMapping("/{flightId}")
    public Flight updateFlight(@PathVariable Long flightId, @RequestBody Flight updatedFlight){
        return flightService.updateFlight(flightId,updatedFlight);
    }

    @DeleteMapping("/{flightId}")
    public void deleteFlight(@PathVariable Long flightId){
        flightService.deleteFlight(flightId);
    }
}
