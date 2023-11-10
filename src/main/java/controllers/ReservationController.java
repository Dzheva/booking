package controllers;

import models.Reservation;
import models.User;
import services.FlightService;
import services.ReservationService;

import java.util.ArrayList;
import java.util.List;

public class ReservationController {
    private final ReservationService reservationService;
    private final FlightService flightService;

    public ReservationController() {
        this.reservationService = new ReservationService();
        this.flightService = new FlightService();
    }

    public void addReservation(Reservation reservation){
            flightService.decreaseSeats(reservation.getFlightId(),reservation.getPassengers().size());
            reservationService.addReservation(reservation);
    }

    public void cancelReservation(Reservation reservation){
        flightService.increaseSeats(reservation.getFlightId(), reservation.getPassengers().size());
        deleteReservationById(reservation.getId());
    }

    public void deleteReservationById(int id){
        reservationService.deleteReservationById(id);
    }


}
