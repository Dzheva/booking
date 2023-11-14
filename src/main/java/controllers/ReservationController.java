package controllers;

import models.Flight;
import models.Passenger;
import models.Reservation;
import models.User;
import services.ReservationService;

import java.util.List;
import java.util.Optional;

public class ReservationController {
    private final ReservationService reservationService;

    public ReservationController() {
        this.reservationService = new ReservationService();
    }

    public void createReservation(Flight flight, User user, List<Passenger> passengers) {
        reservationService.createReservation(flight, user, passengers);
    }

    public List<Reservation> getUserReservations(int userId) {
        return reservationService.getUserReservations(userId);
    }

    public Optional<Reservation> getUserReservation(int userId, int reservationId) {
        return reservationService.getUserReservation(userId, reservationId);
    }

    public void cancelReservation(Reservation reservation, Flight flight) {
        reservationService.cancelReservation(reservation, flight);
    }

    public void saveAll() {
        reservationService.saveAll();
    }
}
