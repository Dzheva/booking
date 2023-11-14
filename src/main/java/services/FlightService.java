package services;

import daos.FlightDAO;
import entities.GeneratedFlight;
import models.Flight;
import utilities.FlightGenerator;

import java.time.LocalDateTime;
import java.util.List;

public class FlightService {
    private final FlightDAO flightDAO;

    public FlightService() {
        this.flightDAO = new FlightDAO();
        if (getAllFlights().isEmpty()) generateAndAddFlights(50);
    }

    public List<Flight> getAllFlights() {
        return flightDAO.getAll();
    }

    public Flight getFlight(int id) {
        return flightDAO.get(id);
    }

    public List<Flight> findFlights(String destination, int numberOfPassengers) {
        return getAllFlights().stream().filter(flight -> flight.getDestination().equalsIgnoreCase(destination)
                && flight.getSeatsAvailable() >= numberOfPassengers + 1).toList();
    }

    public void createFlight(String origin, String destination,
                             LocalDateTime departureTime, LocalDateTime arrivalTime, int seatsAvailable) {
        Flight flight = new Flight(flightDAO.getNextId(), origin, destination,
                departureTime, arrivalTime, seatsAvailable);
        flightDAO.create(flight);
    }


    public void generateAndAddFlights(int count) {
        List<GeneratedFlight> generatedFlights = FlightGenerator.generate(count);
        generatedFlights.forEach(generatedFlight -> createFlight(
                generatedFlight.origin(), generatedFlight.destination(),
                generatedFlight.departureTime(), generatedFlight.arrivalTime(),
                generatedFlight.seatsAvailable()));
    }

    public void saveAll() {
        flightDAO.saveAll();
    }
}
