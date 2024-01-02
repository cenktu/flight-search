package com.cenktu.flightsearch.controller;


import com.cenktu.flightsearch.model.Flight;
import com.cenktu.flightsearch.request.FlightRequest;
import com.cenktu.flightsearch.service.FlightService;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.time.LocalDateTime;
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
    public ResponseEntity<List<Flight>> getAllFlights(){
        return ResponseEntity.ok(flightService.getAllFlights());

    }

    @GetMapping ("/{flightId}")
    public ResponseEntity<Flight> getSingleFlight(@PathVariable Long flightId){
        Optional<Flight> optionalFlight = flightService.getSingleFlight(flightId);
        if(optionalFlight.isPresent()){
            Flight flight = optionalFlight.get();
            return ResponseEntity.ok(flight);
        }

        return ResponseEntity.notFound().build();
    }

    @GetMapping("/search")
    public ResponseEntity<List<Flight>> searchFlights(@RequestParam String departureCity,
                                                      @RequestParam String arrivalCity,
                                                      @RequestParam  @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) LocalDate departureDate,
                                                      @RequestParam(required = false) @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) LocalDate returnDate){
        List<Flight> flights = flightService.searchFlights(departureCity, arrivalCity, departureDate, returnDate);
        return ResponseEntity.ok(flights);
    }

    @PostMapping
    public ResponseEntity<Flight> createFlight(@RequestBody FlightRequest newFlightRequest){
        return ResponseEntity.ok(flightService.createFlight(newFlightRequest));
    }

    @PutMapping("/{flightId}")
    public ResponseEntity<Flight> updateFlight(@PathVariable Long flightId, @RequestBody FlightRequest updatedFlightRequest){
        Optional<Flight> optionalFlight = flightService.getSingleFlight(flightId);
        if(optionalFlight.isPresent()){
            return ResponseEntity.ok(flightService.updateFlight(flightId,updatedFlightRequest));
        }
        return ResponseEntity.notFound().build();
    }

    @DeleteMapping("/{flightId}")
    public ResponseEntity<String> deleteFlight(@PathVariable Long flightId){
        Optional<Flight> optionalFlight = flightService.getSingleFlight(flightId);
        if(optionalFlight.isPresent()){
            flightService.deleteFlight(flightId);
            return ResponseEntity.ok("Flight deleted successfully");
        }
        return ResponseEntity.notFound().build();
    }


}
