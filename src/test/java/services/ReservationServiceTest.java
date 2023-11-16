package services;

import daos.ReservationDAO;
import models.Flight;
import models.Passenger;
import models.Reservation;
import models.User;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.assertTrue;

class ReservationServiceTest {
    private ReservationService reservationService;

    @BeforeEach
    void setUp(){
        reservationService = new ReservationService();
    }

    @Test
    void getAllReservations() {
        List<Reservation> reservations = reservationService.getAllReservations();

        assertAll(
                () -> assertNotNull(reservations),
                () -> assertFalse(reservations.isEmpty()),
                () -> assertTrue(reservations.size() > 1)
        );
    }

    @Test
    void createReservation() {
        Flight testFlight = new Flight(101, "Origin", "Destination", LocalDateTime.now(), LocalDateTime.now().plusHours(2), 10);
        User testUser = new User(101, "test", "test", "Test", "Test");
        List<Passenger> testPassengers = List.of(new Passenger("Passenger1", "LastName1"), new Passenger("Passenger2", "LastName2"));
        reservationService.createReservation(testFlight, testUser, testPassengers);

        int x = reservationService.getAllReservations().size() - 1;
        Reservation createdReservation = reservationService.getAllReservations().get(x);
        assertAll(
                () -> assertFalse(reservationService.getAllReservations().isEmpty()),
                () -> assertEquals(createdReservation.flightId(), testFlight.getId()),
                () -> assertEquals(createdReservation.userId(), testUser.id())
        );
    }

    @Test
    void getUserReservations() {
        Flight testFlight = new Flight(101, "Origin", "Destination", LocalDateTime.now(), LocalDateTime.now().plusHours(2), 10);
        User testUser = new User(101, "test", "test", "Test", "Test");
        List<Passenger> testPassengers = List.of(new Passenger("Passenger1", "LastName1"), new Passenger("Passenger2", "LastName2"));
        reservationService.createReservation(testFlight, testUser, testPassengers);

        List<Reservation> user101 = reservationService.getUserReservations(101);
        Reservation testReservation = user101.get(0);

        assertAll(
                () -> assertFalse(user101.isEmpty()),
                () -> assertTrue(user101.size() == 1),
                () -> assertEquals(testReservation.userId(),101)
        );
    }

    @Test
    void getUserReservation() {
        Flight testFlight = new Flight(101, "Origin", "Destination", LocalDateTime.now(), LocalDateTime.now().plusHours(2), 10);
        User testUser = new User(101, "test", "test", "Test", "Test");
        List<Passenger> testPassengers = List.of(new Passenger("Passenger1", "LastName1"), new Passenger("Passenger2", "LastName2"));
        reservationService.createReservation(testFlight, testUser, testPassengers);

        List<Reservation> user101 = reservationService.getUserReservations(101);
        Reservation testReservation = user101.get(0);
        Optional<Reservation> expectedReservation = reservationService.getUserReservation(testReservation.userId(), testReservation.id());

        assertAll(
                () -> assertFalse(expectedReservation.isEmpty()),
                () -> assertTrue(expectedReservation.isPresent()),
                () -> assertEquals(expectedReservation.get().userId(), testReservation.userId()),
                () -> assertEquals(expectedReservation.get().flightId(), testReservation.flightId())
        );
    }

    @Test
    void cancelReservation() {
        Flight testFlight = new Flight(101, "Origin", "Destination", LocalDateTime.now(), LocalDateTime.now().plusHours(2), 10);
        User testUser = new User(101, "test", "test", "Test", "Test");
        List<Passenger> testPassengers = List.of(new Passenger("Passenger1", "LastName1"), new Passenger("Passenger2", "LastName2"));
        reservationService.createReservation(testFlight, testUser, testPassengers);

        List<Reservation> user101 = reservationService.getUserReservations(101);
        Reservation testReservation = user101.get(0);

        assertTrue(reservationService.getAllReservations().contains(testReservation));
        assertTrue(testFlight.getSeatsAvailable() == 7);

        reservationService.cancelReservation(testReservation, testFlight);

        assertFalse(reservationService.getAllReservations().contains(testReservation));
        assertTrue(testFlight.getSeatsAvailable() == 10);
    }
}