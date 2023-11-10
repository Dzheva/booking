package services;

import daos.ReservationDAO;
import models.Flight;
import models.Reservation;
import models.User;

import java.util.ArrayList;
import java.util.List;

public class ReservationService {
    private final ReservationDAO reservationDAO;

    public ReservationService() {
        this.reservationDAO = new ReservationDAO();
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
