package services;

import daos.ReservationDAO;
import models.Flight;
import models.Passenger;
import models.Reservation;
import models.User;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class ReservationService {
    private final ReservationDAO reservationDAO;

    public ReservationService() {
        this.reservationDAO = new ReservationDAO();
    }

    public List<Reservation> getAllReservations() {
        return reservationDAO.getAll();
    }

    public void createReservation(Flight flight, User user, List<Passenger> passengers) {
        ArrayList<Passenger> allPassengers = new ArrayList<>(passengers);
        allPassengers.add(new Passenger(user.firstName(), user.lastName()));
        Reservation reservation = new Reservation(reservationDAO.getNextId(), flight.getId(), user.id(), allPassengers);
        flight.setSeatsAvailable(flight.getSeatsAvailable() - allPassengers.size());
        reservationDAO.create(reservation);
    }

    public List<Reservation> getUserReservations(int userId) {
        return getAllReservations().stream()
                .filter(reservation -> reservation.userId() == userId).toList();
    }

    public Optional<Reservation> getUserReservation(int userId, int reservationId) {
        return getUserReservations(userId).stream()
                .filter(reservation -> reservation.id() == reservationId).findFirst();
    }

    public void cancelReservation(Reservation reservation, Flight flight) {
        reservationDAO.delete(reservation.id());
        flight.setSeatsAvailable(flight.getSeatsAvailable() + reservation.passengers().size());
    }

    public void saveAll() {
        reservationDAO.saveAll();
    }
}
