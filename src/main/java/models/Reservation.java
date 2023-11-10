package models;

import java.io.Serializable;
import java.util.List;
import java.util.Objects;

public class Reservation implements Serializable {
    private static int idCounter = 1;
    private final int id;
    private final int userId;
    private final int flightId;
    private final List<User> passengers;

    public Reservation(int userId, int flightId, List<User> passengers) {
        this.id = idCounter++;
        this.userId = userId;
        this.flightId = flightId;
        this.passengers = passengers;
    }

    public int getId() {
        return id;
    }

    public int getUserId() {
        return userId;
    }

    public int getFlightId() {
        return flightId;
    }

    public List<User> getPassengers() {
        return passengers;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Reservation that)) return false;

        if (id != that.id) return false;
        if (userId != that.userId) return false;
        if (flightId != that.flightId) return false;
        return Objects.equals(passengers, that.passengers);
    }

    @Override
    public int hashCode() {
        int result = id;
        result = 31 * result + userId;
        result = 31 * result + flightId;
        result = 31 * result + (passengers != null ? passengers.hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        return "Reservation{" +
                "id=" + id +
                ", userId=" + userId +
                ", flightId=" + flightId +
                ", passengers=" + passengers +
                '}';
    }
}
