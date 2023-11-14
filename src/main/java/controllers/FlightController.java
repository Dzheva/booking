package controllers;

import models.Flight;
import services.FlightService;

import java.util.List;

public class FlightController {
    private final FlightService flightService;

    public FlightController() {
        this.flightService = new FlightService();
    }

    public List<Flight> getAllFlights() {
        return flightService.getAllFlights();
    }

    public Flight getFlight(int id) {
        return flightService.getFlight(id);
    }

    public List<Flight> findFlights(String destination, int numberOfPassengers) {
        return flightService.findFlights(destination, numberOfPassengers);
    }

    public void saveAll() {
        flightService.saveAll();
    }
}
