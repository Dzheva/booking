package entities;

import java.time.LocalDateTime;

public record GeneratedFlight(String origin, String destination,
                              LocalDateTime departureTime, LocalDateTime arrivalTime, int seatsAvailable) {
    @Override
    public String toString() {
        return "GeneratedFlight{" +
                "origin='" + origin + '\'' +
                ", destination='" + destination + '\'' +
                ", departureTime=" + departureTime +
                ", arrivalTime=" + arrivalTime +
                ", seatsAvailable=" + seatsAvailable +
                '}';
    }
}
