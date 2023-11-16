package services;

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

class ReservationServiceTest {
    private ReservationService reservationService;
    private int initialSize;
    private Flight flight;
    private User user;
    private Reservation reservation;

    @BeforeEach
    void setUp() {
        reservationService = new ReservationService();
        initialSize = reservationService.getAllReservations().size();
        user = new User(101, "test", "test", "firstName", "lastName");
        flight = new Flight(101, "origin", "destination",
                LocalDateTime.now(), LocalDateTime.now().plusHours(2), 10);
        List<Passenger> passengers = List.of(
                new Passenger("firstName", "lastName"),
                new Passenger("firstName", "lastName"));

        reservationService.createReservation(flight, user, passengers);
        List<Reservation> reservations = reservationService.getUserReservations(101);
        reservation = reservations.get(0);
    }

    @Test
    void getAllReservations() {
        List<Reservation> reservations = reservationService.getAllReservations();
        assertFalse(reservations.isEmpty());
        assertEquals(initialSize + 1, reservations.size());
    }

    @Test
    void createReservation() {
        assertTrue(reservationService.getAllReservations().contains(reservation));
    }

    @Test
    void getUserReservations() {
        List<Reservation> userReservations = reservationService.getUserReservations(101);
        assertFalse(userReservations.isEmpty());
        assertTrue(userReservations.contains(reservation));
    }

    @Test
    void getUserReservation() {
        Optional<Reservation> userReservation =
                reservationService.getUserReservation(reservation.userId(), reservation.id());
        assertTrue(userReservation.isPresent());
        assertEquals(reservation.userId(), userReservation.get().userId());
        assertEquals(reservation.flightId(), userReservation.get().flightId());
    }

    @Test
    void cancelReservation() {
        int userReservationsSize = reservationService.getUserReservations(user.id()).size();
        int seatsAvailable = flight.getSeatsAvailable();
        reservationService.cancelReservation(reservation, flight);
        assertEquals(userReservationsSize - 1, reservationService.getUserReservations(user.id()).size());
        assertFalse(reservationService.getAllReservations().contains(reservation));
        assertEquals(seatsAvailable + reservation.passengers().size(), flight.getSeatsAvailable());
    }
}
