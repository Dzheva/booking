package controllers;

import models.Flight;
import models.Reservation;
import services.FlightService;
import services.ReservationService;

import java.time.LocalDate;
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

    public void addListFlight(List<Flight> flightsList){
        flightService.addListFlight(flightsList);
    }

    public void deleteFlightById(int id) {
        flightService.deleteFlightById(id);
    }

    public void generateFlights() {
        flightService.generateFlights();
    }

    public void searchFlights(String destination, LocalDate arrivalDate, int quantityPassengers) {
        flightService.searchFlights(destination, arrivalDate, quantityPassengers);
    }
}
