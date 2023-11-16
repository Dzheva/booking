package services;

import models.Flight;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class FlightServiceTest {
    private FlightService flightService;
    @BeforeEach
    void setUp(){
        flightService = new FlightService();
    }

    @Test
    void getAllFlights() {
        List<Flight> flights = flightService.getAllFlights();

        assertAll(
                () -> assertNotNull(flights),
                () -> assertFalse(flights.isEmpty()),
                () -> assertTrue(flights.size() > 1)
        );
    }

    @Test
    void getFlight() {
        //Flights ID starts from 1
        assertAll(
                () -> assertNull(flightService.getFlight(0)),
                () -> assertNotNull(flightService.getFlight(1)),
                () -> assertNotNull(flightService.getFlight(2))
        );
    }

    @Test
    void findFlights() {
        String destination = "OneWayTicket";
        int numberOfPassengers = 2;

        flightService.createFlight("Origin1", destination, LocalDateTime.now(), LocalDateTime.now().plusHours(2), 12);
        flightService.createFlight("Origin2", "AnotherDestination", LocalDateTime.now(), LocalDateTime.now().plusHours(2), 8);
        flightService.createFlight("Origin3", destination, LocalDateTime.now(), LocalDateTime.now().plusHours(2), 15);

        List<Flight> foundFlights = flightService.findFlights(destination, numberOfPassengers);
        assertNotNull(foundFlights);

        assertTrue(foundFlights.stream().allMatch(flight ->
                flight.getDestination().equalsIgnoreCase(destination) &&
                        flight.getSeatsAvailable() >= numberOfPassengers + 1));

        assertEquals(foundFlights.size(), 2);
    }

    @Test
    void createFlight() {
        String origin = "Origin";
        String destination = "Destination";
        LocalDateTime departureTime = LocalDateTime.now();
        LocalDateTime arrivalTime = departureTime.plusHours(2);
        int seatsAvailable = 100;

        flightService.createFlight(origin, destination, departureTime, arrivalTime, seatsAvailable);

        Flight createdFlight = flightService.getFlight(flightService.getAllFlights().size());

        assertAll(
                () -> assertEquals(origin, createdFlight.getOrigin()),
                () -> assertEquals(destination, createdFlight.getDestination()),
                () -> assertEquals(departureTime, createdFlight.getDepartureTime()),
                () -> assertEquals(arrivalTime, createdFlight.getArrivalTime()),
                () -> assertEquals(seatsAvailable, createdFlight.getSeatsAvailable())
        );
    }

    @Test
    void generateAndAddFlights() {
        int count = 5;
        int initialSize = flightService.getAllFlights().size();

        flightService.generateAndAddFlights(count);

        List<Flight> flights = flightService.getAllFlights();
        int finalSize = flights.size();

        assertEquals(finalSize, initialSize + count);
    }
}