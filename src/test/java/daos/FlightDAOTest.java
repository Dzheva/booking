package daos;

import models.Flight;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

class FlightDAOTest {
    private FlightDAO flightDAO;
    private int initialSize;
    private Flight flight;

    @BeforeEach
    void setUp() {
        flightDAO = new FlightDAO();
        initialSize = flightDAO.getAll().size();
        flight = new Flight(flightDAO.getNextId(), "New York", "Milan",
                LocalDateTime.of(2023, 10, 15, 14, 30),
                LocalDateTime.of(2023, 10, 15, 18, 15), 50);
        flightDAO.create(flight);
    }

    @Test
    void create() {
        assertTrue(flightDAO.getAll().contains(flight));
        assertEquals(initialSize + 1, flightDAO.getAll().size());
    }

    @Test
    void get() {
        assertEquals(flightDAO.get(flight.getId()), flight);
    }

    @Test
    void getAll() {
        assertTrue(flightDAO.getAll().contains(flight));
    }

    @Test
    void getNextId() {
        assertEquals(flightDAO.getNextId(), flightDAO.getAll().size() + 1);
    }

    @Test
    void saveAll() {
        flightDAO.saveAll();
        assertEquals(flightDAO.getAll().size(), new FlightDAO().getAll().size());
    }
}
