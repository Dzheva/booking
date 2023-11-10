package services;

import daos.FileFlightDAO;
import daos.FlightDAO;
import models.Flight;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class FlightService {
    private final FlightDAO flightDAO;

    public FlightService() {
        this.flightDAO = new FileFlightDAO();
    }

    public List<Flight> getAllFlights() {
        return flightDAO.getAllFlights();
    }

    public Flight getFlightById(int id) {
        return flightDAO.getFlightById(id);
    }

    public void addFlight(Flight flight) {
        flightDAO.addFlight(flight);
    }

    public void addListFlight(List<Flight> flightsList){
        flightDAO.addListFlight(flightsList);
    }

    public void deleteFlightById(int id) {
        flightDAO.deleteFlightById(id);
    }

    public void generateFlights() {
        int quantity = 50; // в сервисе количество рейсов можно регулировать
        List<Flight> flightList = generateFlights(quantity);
        addListFlight(flightList);
        //flightDAO.addListFlight(flightList);
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

        return flights.stream().distinct().collect(Collectors.toList());
    }

    public void decreaseSeats(int idFlight, int quantitySeats){
        int updatedQuantitySeats = getFlightById(idFlight).getSeatsAvailable() - quantitySeats;
        getFlightById(idFlight).setSeatsAvailable(updatedQuantitySeats);
    }

    public void increaseSeats(int idFlight, int quantitySeats){
        int updatedQuantitySeats = getFlightById(idFlight).getSeatsAvailable() + quantitySeats;
        getFlightById(idFlight).setSeatsAvailable(updatedQuantitySeats);
    }

    //может хватить только метода updateQuantitySeats,
    // но на всякий случай добавил методы decreaseSeats и increaseSeats
    public void updateQuantitySeats(int idFlight, int updatedQuantitySeats){
        getFlightById(idFlight).setSeatsAvailable(updatedQuantitySeats);
    }

    public List<Flight> searchFlights(String destination, LocalDate arrivalDate, int quantityPassengers){
        List<Flight> result = new ArrayList<>();
        List<Flight> flightList = getAllFlights();

        for(Flight flight : flightList){
            if(flight.getDestination().equalsIgnoreCase(destination)
                    &&flight.getArrivalTime().toLocalDate().isEqual(arrivalDate)
                    &&(flight.getSeatsAvailable() >= quantityPassengers)){
                result.add(flight);
            }
        }

        return result;
    }
}
