package daos;

import models.Flight;
import utilities.ResourceHandler;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class FileFlightDAO implements FlightDAO {
    private final String flightDataFile = "flights.ser";
    //private final List<Flight> flightsList;
    private final Optional<List<Flight>> flightsList;


    @SuppressWarnings("unchecked")
    public FileFlightDAO() {
        Object object = ResourceHandler.loadData(flightDataFile);
        //flightsList = object == null ? new ArrayList<>() : (List<Flight>) object;
        flightsList = Optional.ofNullable(object == null ? new ArrayList<>() : (List<Flight>) object);
    }

    @Override
    public List<Flight> getAllFlights() {
        //return flightsList;
        return flightsList.orElseGet(ArrayList::new);
    }

    @Override
    public Flight getFlightById(int id) {
//        for (Flight flight : flightsList) {
//            if (flight.getId() == id) return flight;
//        }
//        return null;

        return flightsList
                .flatMap(list -> list.stream().filter(flight -> flight.getId() == id).findFirst())
                .orElse(null);
    }

    @Override
    public void addFlight(Flight flight) {
//        flightsList.add(flight);
//        ResourceHandler.saveData(flightDataFile, flightsList);

        List<Flight> updatedFlights = flightsList.orElseGet(ArrayList::new);
        updatedFlights.add(flight);
        ResourceHandler.saveData(flightDataFile, updatedFlights);
    }

    @Override
    public void deleteFlightById(int id) {
//        flightsList.removeIf(flight -> flight.getId() == id);
//        ResourceHandler.saveData(flightDataFile, flightsList);

        List<Flight> updatedFlights = flightsList.orElseGet(ArrayList::new);
        updatedFlights.removeIf(flight -> flight.getId() == id);
        ResourceHandler.saveData(flightDataFile, updatedFlights);
    }
}
