package daos;

import constants.ResourceName;
import models.Flight;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import utilities.ResourceHandler;

import java.io.File;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

class FlightDAOTest {
    private FlightDAO flightDAO;
    private Flight flight;

    @BeforeEach
    void setUp() {
        flightDAO = new FlightDAO();
        LocalDateTime departureTime = LocalDateTime.of(2023, 10, 15, 14, 30);
        LocalDateTime arriveTime = LocalDateTime.of(2023, 10, 15, 18, 15);
        flight = new Flight(flightDAO.getNextId(), "New York", "Milan", departureTime, arriveTime, 50);
    }

    @Test
    void create() {
        flightDAO.create(flight);
        assertAll(
                () -> assertTrue(flightDAO.getAll().contains(flight)),
                () -> assertFalse(flightDAO.getAll().contains(null))
        );
    }

    @Test
    void get() {
            flightDAO.create(flight);
            assertEquals(flightDAO.get(flight.getId()), flight);
    }

    @Test
    void getAll() {
        //We generated 50 flights previously.
        flightDAO.create(flight);

        assertAll(
                () -> assertTrue(flightDAO.getAll().contains(flight)),
                () -> assertEquals(flightDAO.getNextId()-1, flightDAO.getAll().size())
        );
    }

    @Test
    void getNextId(){
        int actual = flightDAO.getNextId();
        int expected = flightDAO.getAll().size()+2;

        assertEquals(expected, actual);
    }

    @Test
    void saveAll(){
        flightDAO.create(flight);
        flightDAO.saveAll();
        int size1 = flightDAO.getAll().size();

        FlightDAO flightDAO2 = new FlightDAO();
        int size2 = flightDAO2.getAll().size();

        assertEquals(size1, size2);
    }
}