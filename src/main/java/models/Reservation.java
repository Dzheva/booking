package models;

import java.io.Serializable;

public class Reservation implements Serializable {
    private final int flightId;
    private final int userId;

    public Reservation(int flightId, int userId) {
        this.flightId = flightId;
        this.userId = userId;
    }

    public int getFlightId() {
        return flightId;
    }

    public int getUserId() {
        return userId;
    }
}
