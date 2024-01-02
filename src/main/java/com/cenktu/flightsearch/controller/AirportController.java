package com.cenktu.flightsearch.controller;

import com.cenktu.flightsearch.model.Airport;
import com.cenktu.flightsearch.service.AirportService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
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
    public ResponseEntity<List<Airport>> getAllAirports(){
        return ResponseEntity.ok(airportService.getAllAirports());
    }

    @GetMapping("/{airportId}")
    public ResponseEntity<Airport> getSingleAirport(@PathVariable Long airportId){
        return ResponseEntity.ok(airportService.getSingleAirport(airportId));
    }


    @PostMapping
    public ResponseEntity<Airport> createAirport(@RequestBody Airport newAirport){
        return ResponseEntity.ok(airportService.createAirport(newAirport));
    }

    @PutMapping("/{airportId}")
    public ResponseEntity<Airport> updateAirport(@PathVariable Long airportId,@RequestBody Airport updatedAirport){
        Airport optionalAirport = airportService.getSingleAirport(airportId);
        if(optionalAirport!=null){
            return ResponseEntity.ok(airportService.updateAirport(airportId,updatedAirport));
        }
        return ResponseEntity.notFound().build();
    }

    @DeleteMapping("/{airportId}")
    public ResponseEntity<String> deleteAirport(@PathVariable Long airportId){
        Airport optionalAirport = airportService.getSingleAirport(airportId);
        if(optionalAirport!=null){
            airportService.deleteAirport(airportId);
            return ResponseEntity.ok("Airport deleted successfully");
        }
        return ResponseEntity.notFound().build();
    }

}
