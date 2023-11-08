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

    @Override
    public void generateAndSaveFlights(int quantity) {
        List<Flight> flights = generateFlights(quantity);
        ResourceHandler.saveData(flightDataFile, flights);
    }

    public static List<Flight> generateFlights(int count) {
        List<Flight> flights = new ArrayList<>();

        String[] origins = {"New York", "Los Angeles", "Chicago", "Miami", "Kyiv"};
        String[] destinations = {"London", "Paris", "Tokyo", "Dubai", "Sydney"};

        for (int i = 0; i < count; i++) {
            String origin = origins[(int) (Math.random() * origins.length)];
            String destination = destinations[(int) (Math.random() * destinations.length)];
            LocalDateTime departureTime = LocalDateTime.now().plusHours((int)(Math.random()*5));
            LocalDateTime arrivalTime = departureTime.plusHours((int)(Math.random()*10));
            int seatsAvailable = (int) (Math.random() * 100);

            Flight flight = new Flight(origin, destination, departureTime, arrivalTime, seatsAvailable);
            flights.add(flight);
        }

        //return flights;
        return flights.stream().distinct().collect(Collectors.toList());
    }
}
