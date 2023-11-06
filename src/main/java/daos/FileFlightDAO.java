package daos;

import models.Flight;
import utilities.ResourceHandler;

import java.util.ArrayList;
import java.util.List;

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
        // Надо доделать проверку существует ли уже такой объект в файле
        flightsList.add(flight);
        ResourceHandler.saveData(flightDataFile, flightsList);
    }

    @Override
    public void deleteFlightById(int id) {
        flightsList.removeIf(flight -> flight.getId() == id);
        ResourceHandler.saveData(flightDataFile, flightsList);
    }
}
