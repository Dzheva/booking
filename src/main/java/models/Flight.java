package models;

import java.io.Serializable;
import java.time.LocalDateTime;

public class Flight implements Serializable {
    private final int id;
    private final String origin;
    private final String destination;
    private final LocalDateTime departureTime;
    private final LocalDateTime arrivalTime;
    private int seatsAvailable;

    public Flight(int id, String origin, String destination,
                  LocalDateTime departureTime, LocalDateTime arrivalTime, int seatsAvailable) {
        this.id = id;
        this.origin = origin;
        this.destination = destination;
        this.departureTime = departureTime;
        this.arrivalTime = arrivalTime;
        this.seatsAvailable = seatsAvailable;
    }

    public int getId() {
        return id;
    }

    public String getOrigin() {
        return origin;
    }

    public String getDestination() {
        return destination;
    }

    public LocalDateTime getDepartureTime() {
        return departureTime;
    }

    public LocalDateTime getArrivalTime() {
        return arrivalTime;
    }

    public int getSeatsAvailable() {
        return seatsAvailable;
    }

    public void setSeatsAvailable(int seatsAvailable) {
        this.seatsAvailable = seatsAvailable;
    }

    @Override
    public String toString() {
        return "Flight{" +
                "id=" + id +
                ", origin='" + origin + '\'' +
                ", destination='" + destination + '\'' +
                ", departureTime=" + departureTime +
                ", arrivalTime=" + arrivalTime +
                ", seatsAvailable=" + seatsAvailable +
                '}';
    }
}
