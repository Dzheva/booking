package services;

import daos.FlightDAO;
import models.Flight;

import java.util.List;

public class FlightService {
    private final FlightDAO flightDAO;

    public FlightService(FlightDAO flightDAO) {
        this.flightDAO = flightDAO;
    }

    public List<Flight> getAllFlights(){
        return flightDAO.getAllFlights();
    }

    public Flight getFlightById(int id) {
        return flightDAO.getFlightById(id);
    }

    public void addFlight(Flight flight) {
        flightDAO.addFlight(flight);
    }

    public void deleteFlightById(int id) {
        flightDAO.deleteFlightById(id);
    }
}
