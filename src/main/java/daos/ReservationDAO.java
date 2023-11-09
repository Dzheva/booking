package daos;

import models.Flight;
import models.Reservation;
import utilities.ResourceHandler;

import java.util.ArrayList;
import java.util.List;

public class ReservationDAO {
    private final String FILENAME = "reservations.ser";

    private final List<Reservation> reservationList;

    public ReservationDAO() {
        Object object = ResourceHandler.loadData(FILENAME);
        reservationList = object == null ? new ArrayList<>() : (List<Reservation>) object;
    }

    public List<Reservation> getAllReservation(){
        return reservationList;
    }

    public Reservation getReservationById(int id){
        for (Reservation reservation : reservationList) {
            if (reservation.getId() == id) return reservation;
        }
        return null;
    }

    public void addReservation(Reservation reservation){
        reservationList.add(reservation);
        ResourceHandler.saveData(FILENAME, reservationList);
    }

    public void addListReservation(List<Reservation> reservationList){
        ResourceHandler.saveData(FILENAME, reservationList);
    }

    public void deleteReservationById(int id){
        reservationList.removeIf(reservation -> reservation.getId() == id);
        addListReservation(reservationList);
    }

}
