package controllers;

import models.Flight;
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
            List<Flight> flightList = flightService.getAllFlights();

            if(flightList.contains(getFlightByReservation(reservation))){
                flightService.decreaseSeats(getIdFlightByReservation(reservation), reservation.getPassengers().size());
                reservationService.addReservation(reservation);
                System.out.println("The reservation is completed.");
            } else {
                System.out.println("This flight doesn't exist.");
            }
    }

    public void cancelReservation(Reservation reservation){
            List<Reservation> reservationList = reservationService.getAllReservation();

            if(reservationList.contains(reservation)){
                flightService.increaseSeats(getIdFlightByReservation(reservation), reservation.getPassengers().size());
                deleteReservationById(reservation.getId());
                System.out.println("The reservation is canceled.");
            } else {
                System.out.println("This reservation doesn't exist.");
            }
    }

    public void deleteReservationById(int id){
        reservationService.deleteReservationById(id);
    }

    public Flight getFlightByReservation(Reservation reservation){
        return flightService.getFlightById(getIdFlightByReservation(reservation));
    }

    public int getIdFlightByReservation(Reservation reservation){
        return reservation.getFlightId();
    }
}
