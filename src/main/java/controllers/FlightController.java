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

    public Flight getFlightById(int id) {
        return flightService.getFlightById(id);
    }

    public void addFlight(Flight flight) {
        flightService.addFlight(flight);
    }

    public void deleteFlightById(int id) {
        flightService.deleteFlightById(id);
    }

    public void generateFlights() {
        flightService.generateFlights();
    }
}
