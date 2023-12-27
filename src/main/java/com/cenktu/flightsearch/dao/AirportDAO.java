package com.cenktu.flightsearch.dao;

import com.cenktu.flightsearch.model.Airport;
import org.springframework.data.repository.ListCrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AirportDAO extends ListCrudRepository<Airport,Long> {
}
