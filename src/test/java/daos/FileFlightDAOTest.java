package daos;

import models.Flight;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import services.FlightService;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

class FileFlightDAOTest {
    private Flight flight;
    private FileFlightDAO fileFlightDAO;

    @BeforeEach
    void setUp() {
        LocalDateTime departureTime = LocalDateTime.of(2023, 10, 15, 14, 30);
        LocalDateTime arriveTime = LocalDateTime.of(2023, 10, 15, 18, 15);
        flight = new Flight("New York", "Milan", departureTime, arriveTime, 50);
        fileFlightDAO = new FileFlightDAO();
        fileFlightDAO.addFlight(flight);
    }

    @Test
    void createAndRetrieveFlights() {
        List<Flight> list = fileFlightDAO.getAllFlights();
        assertTrue(list.contains(flight));
    }

    @Test
    void getFlightById() {
        assertEquals(flight, fileFlightDAO.getFlightById(flight.getId()));
        assertNull(fileFlightDAO.getFlightById(10));
        assertNull(fileFlightDAO.getFlightById(-1));
    }

    @Test
    void deleteFlightById() {
        fileFlightDAO.deleteFlightById(1);
        assertNull(fileFlightDAO.getFlightById(1));
    }

    @Test
    void testAddFlight() {
        List<Flight> flightList = fileFlightDAO.getAllFlights();

        int initialSize = flightList.size();

        LocalDateTime newDepartureTime = LocalDateTime.of(2023, 10, 16, 14, 30);
        LocalDateTime newArriveTime = LocalDateTime.of(2023, 10, 16, 18, 15);
        Flight newFlight = new Flight("Los Angeles", "London", newDepartureTime, newArriveTime, 75);

        fileFlightDAO.addFlight(newFlight);

        flightList = fileFlightDAO.getAllFlights();

        int finalSize = flightList.size();

        assertTrue(finalSize == initialSize + 1);

        Flight addedFlight = flightList.get(initialSize);

        assertEquals(newFlight.getOrigin(), addedFlight.getOrigin());
        assertEquals(newFlight.getDestination(), addedFlight.getDestination());
        assertEquals(newFlight.getDepartureTime(), addedFlight.getDepartureTime());
        assertEquals(newFlight.getArrivalTime(), addedFlight.getArrivalTime());
        assertEquals(newFlight.getSeatsAvailable(), addedFlight.getSeatsAvailable());
    }

    @Test
    void testAddListFlight(){
        List<Flight> list = new ArrayList<>();
        list = fileFlightDAO.getAllFlights();
        fileFlightDAO.deleteFlightById(1);
        fileFlightDAO.addListFlight(list);
        assertFalse(fileFlightDAO.getAllFlights().isEmpty());
    }

}
