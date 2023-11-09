package services;

import daos.ReservationDAO;
import models.Reservation;

import java.util.List;

public class ReservationService {
    private final ReservationDAO reservationDAO;

    public ReservationService(ReservationDAO reservationDAO) {
        this.reservationDAO = reservationDAO;
    }

    public List<Reservation> getAllReservation(){
        return reservationDAO.getAllReservation();
    }

    public Reservation getReservationById(int id){
        return reservationDAO.getReservationById(id);
    }

    public void addReservation(Reservation reservation){
        reservationDAO.addReservation(reservation);
    }

    public void addListReservation(List<Reservation> reservationList){
        reservationDAO.addListReservation(reservationList);
    }

    public void deleteReservationById(int id){
        reservationDAO.deleteReservationById(id);
    }


}
