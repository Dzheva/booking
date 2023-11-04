package models;

import java.io.Serializable;

public class Flight implements Serializable {
    private final String origin;
    private final String destination;
    private final long timestamp;
    private int seatsAvailable;

    public Flight(String origin, String destination, long timestamp, int seatsAvailable) {
        this.timestamp = timestamp;
        this.seatsAvailable = seatsAvailable;
        this.origin = origin;
        this.destination = destination;
    }

    public String getOrigin() {
        return origin;
    }

    public String getDestination() {
        return destination;
    }

    public long getTimestamp() {
        return timestamp;
    }

    public int getSeatsAvailable() {
        return seatsAvailable;
    }

    public void setSeatsAvailable(int seatsAvailable) {
        this.seatsAvailable = seatsAvailable;
    }
}
