package models;

import java.io.Serializable;
import java.util.List;

public record Reservation(int id, int flightId, int userId,
                          List<Passenger> passengers) implements Serializable {
    @Override
    public String toString() {
        return "Reservation{" +
                "id=" + id +
                ", flightId=" + flightId +
                ", userId=" + userId +
                ", passengers=" + passengers +
                '}';
    }
}
