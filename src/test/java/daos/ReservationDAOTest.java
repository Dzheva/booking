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
    private Reservation reservation;

    @BeforeEach
    void setUp(){
        reservationDAO = new ReservationDAO();
        List<Passenger> passengerList = new ArrayList<>();

        Passenger passenger  = new Passenger("Andrii", "Shevchenko");
        passengerList.add(passenger);

        reservation = new Reservation(reservationDAO.getNextId(), 1, 1, passengerList);
        reservationDAO.create(reservation);
    }

    @Test
    void delete() {
        int expected = reservationDAO.getAll().size() - 1;

        reservationDAO.delete(reservation.id());
        int actual = reservationDAO.getAll().size();

        assertAll(
                () -> assertEquals(expected, actual),
                () -> assertFalse(reservationDAO.getAll().contains(reservation))
        );
    }

    @Test
    void create() {
        assertAll(
                () -> assertTrue(reservationDAO.getAll().contains(reservation)),
                () -> assertEquals(reservation, reservationDAO.get(reservation.id()))
        );
    }

    @Test
    void get() {
         assertEquals(reservationDAO.get(reservation.id()), reservation);
    }

    @Test
    void getAll() {
        assertAll(
                () -> assertFalse(reservationDAO.getAll().isEmpty()),
                () -> assertTrue(reservationDAO.getAll().contains(reservation)),
                () -> assertEquals(reservationDAO.getNextId()-1, reservationDAO.getAll().size())
        );
    }

    @Test
    void getNextId() {
        int actual = reservationDAO.getNextId();
        int expected = reservationDAO.getAll().size() + 1;
        assertEquals(expected, actual);
    }

    @Test
    void saveAll(){
        reservationDAO.saveAll();
        int size1 = reservationDAO.getAll().size();

        ReservationDAO reservationDAO2 = new ReservationDAO();
        int size2 = reservationDAO2.getAll().size();

        assertEquals(size1, size2);
    }
}