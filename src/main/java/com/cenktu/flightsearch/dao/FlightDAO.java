package com.cenktu.flightsearch.dao;

import com.cenktu.flightsearch.model.Flight;
import org.springframework.data.repository.ListCrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface FlightDAO extends ListCrudRepository<Flight,Long> {
}
