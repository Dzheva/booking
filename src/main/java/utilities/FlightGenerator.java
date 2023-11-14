package utilities;

import entities.GeneratedFlight;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public final class FlightGenerator {
    public static List<GeneratedFlight> generate(int quantity) {
        List<GeneratedFlight> flights = new ArrayList<>();
        String[] origins = {"New York", "Los Angeles", "Chicago", "Miami", "Kyiv"};
        String[] destinations = {"London", "Paris", "Tokyo", "Dubai", "Sydney"};

        for (int i = 0; i < quantity; i++) {
            String origin = origins[(int) (Math.random() * origins.length)];
            String destination = destinations[(int) (Math.random() * destinations.length)];
            LocalDateTime departureTime = LocalDateTime.now().plusHours((int) (Math.random() * 5));
            LocalDateTime arrivalTime = departureTime.plusHours((int) (Math.random() * 10));
            int seatsAvailable = (int) (Math.random() * 100);

            flights.add(new GeneratedFlight(origin, destination, departureTime, arrivalTime, seatsAvailable));
        }
        return flights.stream().distinct().collect(Collectors.toList());
    }
}
