package daos;

import models.Flight;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertTrue;

class FileFlightDAOTest {
    FileFlightDAO fileFlightDAO;

    @BeforeEach
    void setUp() {
        fileFlightDAO = new FileFlightDAO();
    }

    @Test
    void createAndRetrieveFlights() {
        LocalDateTime departureTime = LocalDateTime.of(2023, 10, 15, 14, 30);
        LocalDateTime arriveTime = LocalDateTime.of(2023, 10, 15, 18, 15);
        Flight flight = new Flight("New York", "Milan", departureTime, arriveTime, 50);

        fileFlightDAO.addFlight(flight);
        List<Flight> list = fileFlightDAO.getAllFlights();
        assertTrue(list.contains(flight));
    }
}
