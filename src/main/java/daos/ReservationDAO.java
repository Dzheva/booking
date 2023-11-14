package daos;

import constants.ResourceName;
import models.Reservation;
import utilities.ResourceHandler;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Optional;

public class ReservationDAO implements FileDao<Reservation> {
    private final List<Reservation> reservations;
    private int nextReservationId = 1;

    @SuppressWarnings("unchecked")
    public ReservationDAO() {
        Optional<Object> optional = ResourceHandler.loadData(ResourceName.RESERVATIONS);
        reservations = optional.map(object -> (List<Reservation>) object).orElseGet(ArrayList::new);

        if (!reservations.isEmpty()) {
            reservations.stream().max(Comparator.comparing(Reservation::id))
                    .ifPresent(reservation -> nextReservationId = reservation.id() + 1);
        }
    }

    public void delete(int id) {
        reservations.removeIf(reservation -> reservation.id() == id);
    }

    @Override
    public void create(Reservation reservation) {
        reservations.add(reservation);
    }

    @Override
    public Reservation get(int id) {
        return reservations.stream()
                .filter(reservation -> reservation.id() == id).findFirst().orElse(null);
    }

    @Override
    public List<Reservation> getAll() {
        return reservations;
    }

    @Override
    public int getNextId() {
        return nextReservationId++;
    }

    @Override
    public void saveAll() {
        ResourceHandler.saveData(ResourceName.RESERVATIONS, reservations);
    }
}
