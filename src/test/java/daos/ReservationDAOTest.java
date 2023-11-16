package daos;

import models.Passenger;
import models.Reservation;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class ReservationDAOTest {
    private ReservationDAO reservationDAO;
    private int initialSize;
    private Reservation reservation;

    @BeforeEach
    void setUp() {
        reservationDAO = new ReservationDAO();
        initialSize = reservationDAO.getAll().size();
        List<Passenger> passengerList = new ArrayList<>(
                List.of(new Passenger("Andriy", "Shevchenko")));
        reservation = new Reservation(reservationDAO.getNextId(), 1, 1, passengerList);
        reservationDAO.create(reservation);
    }

    @Test
    void delete() {
        int expectedSize = reservationDAO.getAll().size() - 1;
        reservationDAO.delete(reservation.id());
        assertEquals(expectedSize, reservationDAO.getAll().size());
        assertFalse(reservationDAO.getAll().contains(reservation));
    }

    @Test
    void create() {
        assertTrue(reservationDAO.getAll().contains(reservation));
        assertEquals(reservation, reservationDAO.get(reservation.id()));
        assertEquals(initialSize + 1, reservationDAO.getAll().size());
    }

    @Test
    void get() {
        assertEquals(reservationDAO.get(reservation.id()), reservation);
    }

    @Test
    void getAll() {
        assertTrue(reservationDAO.getAll().contains(reservation));
        assertEquals(reservationDAO.getNextId() - 1, reservationDAO.getAll().size());
    }

    @Test
    void getNextId() {
        assertEquals(reservationDAO.getAll().size() + 1, reservationDAO.getNextId());
    }

    @Test
    void saveAll() {
        reservationDAO.saveAll();
        assertEquals(reservationDAO.getAll().size(), new ReservationDAO().getAll().size());
    }
}
