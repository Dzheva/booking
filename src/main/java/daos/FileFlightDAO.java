package daos;

import models.Flight;
import utilities.ResourceHandler;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class FileFlightDAO implements FlightDAO {
    private final String flightDataFile = "flights.ser";
    private final ResourceHandler resourceHandler;
    private List<Flight> flightsList = new ArrayList<>();

    public FileFlightDAO(ResourceHandler resourceHandler) {
        this.resourceHandler = resourceHandler;
    }

    @Override
    public List<Flight> getAllFlights() {
        while (true) {
           Flight flight = (Flight) resourceHandler.loadData(flightDataFile);
           if (flight == null) break;
            flightsList.add(flight);
        }
        return flightsList;
    }

    @Override
    public Flight getFlightById(int id) {
        flightsList = getAllFlights();
        Flight resultFlight = null;
        for (Flight flight : flightsList){
            if(flight.getId() == id) {
                resultFlight = flight;
                break;
            }
        }
        return resultFlight;
    }

    @Override
    public void addFlight(Flight flight) {
        //надо доделать проверку существует ли уже такой объект в файле
        resourceHandler.saveData(flightDataFile, flight);
    }

    @Override
    public void deleteFlightById(int id){
        flightsList = getAllFlights();
        Flight flightToRemove = getFlightById(id);
        flightsList.remove(flightToRemove);
        resourceHandler.saveData(flightDataFile, flightsList);
    }


    public static void main(String[] args) {
        System.out.println("Test#1");
        LocalDateTime departureTime1 = LocalDateTime.of(2023, 10, 15, 14, 30);
        LocalDateTime arriveTime1 = LocalDateTime.of(2023, 10, 15, 18, 15);
        Flight flight1 = new Flight("New York", "Milan", departureTime1, arriveTime1, 50);

        ResourceHandler resourceHandler1 = new ResourceHandler();
        FileFlightDAO fileFlightDAO = new FileFlightDAO(resourceHandler1);
        fileFlightDAO.addFlight(flight1);
        List<Flight> list = new ArrayList<>();
        list = fileFlightDAO.getAllFlights();
        list.forEach(System.out::println);

    }

}
