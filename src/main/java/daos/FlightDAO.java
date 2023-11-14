package daos;

import constants.ResourceName;
import models.Flight;
import utilities.ResourceHandler;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Optional;

public class FlightDAO implements FileDao<Flight> {
    private final List<Flight> flights;
    private int nextFlightId = 1;

    @SuppressWarnings("unchecked")
    public FlightDAO() {
        Optional<Object> optional = ResourceHandler.loadData(ResourceName.FLIGHTS);
        flights = optional.map(object -> (List<Flight>) object).orElseGet(ArrayList::new);

        if (!flights.isEmpty()) {
            flights.stream().max(Comparator.comparing(Flight::getId))
                    .ifPresent(flight -> nextFlightId = flight.getId() + 1);
        }
    }

    @Override
    public void create(Flight flight) {
        flights.add(flight);
    }

    @Override
    public Flight get(int id) {
        return flights.stream().filter(flight -> flight.getId() == id).findFirst().orElse(null);
    }

    @Override
    public List<Flight> getAll() {
        return flights;
    }

    @Override
    public int getNextId() {
        return nextFlightId++;
    }

    public void saveAll() {
        ResourceHandler.saveData(ResourceName.FLIGHTS, flights);
    }
}
