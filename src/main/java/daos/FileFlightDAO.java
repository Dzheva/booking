package daos;

import models.Flight;
import utilities.ResourceHandler;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

public class FileFlightDAO implements FlightDAO {
    private final String flightDataFile = "flights.ser";

    private final List<Flight> flightsList;

    @SuppressWarnings("unchecked")
    public FileFlightDAO() {
        Object object = ResourceHandler.loadData(flightDataFile);
        flightsList = object == null ? new ArrayList<>() : (List<Flight>) object;
    }

    @Override
    public List<Flight> getAllFlights() {
        return flightsList;
    }

    @Override
    public Flight getFlightById(int id) {
        for (Flight flight : flightsList) {
            if (flight.getId() == id) return flight;
        }
        return null;
    }

    @Override
    public void addFlight(Flight flight) {
        flightsList.add(flight);
        ResourceHandler.saveData(flightDataFile, flightsList);
    }

    @Override
    public void addListFlight(List<Flight> flightsList) {
        ResourceHandler.saveData(flightDataFile, flightsList);
    }

    @Override
    public void deleteFlightById(int id) {
        flightsList.removeIf(flight -> flight.getId() == id);
        ResourceHandler.saveData(flightDataFile, flightsList);
    }

}
