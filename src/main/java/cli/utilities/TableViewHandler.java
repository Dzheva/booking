package cli.utilities;

import cli.constants.TableString;
import models.Flight;
import models.Passenger;
import models.Reservation;

import java.time.format.DateTimeFormatter;
import java.util.List;

public class TableViewHandler {
    private static final DateTimeFormatter FORMATTER = DateTimeFormatter.ofPattern("HH:mm");
    private static final String FLIGHT_TABLE_COLUMNS =
            "| %-3s | %-18s | %-17s | %-16s | %-12s | %-12s |";
    private static final String PASSENGER_TABLE_COLUMNS =
            "| %-7s | %-12s | %-12s |";
    private static final String RESERVATION_TABLE_COLUMNS =
            "| %-12s |  %-4s | %-6s |  %-4s |";
    private static final String FLIGHT_TABLE_SEPARATOR_ROW =
            generateSeparatorRow(List.of(5, 20, 19, 18, 14, 14));
    private static final String PASSENGER_TABLE_SEPARATOR_ROW =
            generateSeparatorRow(List.of(9, 14, 14));
    private static final String RESERVATION_TABLE_SEPARATOR_ROW =
            generateSeparatorRow(List.of(14, 7, 8, 7));

    private static String getFlightTableHeader() {
        return String.format(FLIGHT_TABLE_COLUMNS,
                TableString.ID, TableString.ORIGIN, TableString.DESTINATION,
                TableString.DEPARTURE_TIME, TableString.ARRIVAL_TIME, TableString.SEATS);
    }

    private static String getPassengerTableHeader() {
        return String.format(PASSENGER_TABLE_COLUMNS,
                TableString.PASSENGER, TableString.NAME, TableString.SURNAME);
    }

    private static String createFlightTableRow(Flight flight) {
        return String.format(FLIGHT_TABLE_COLUMNS,
                flight.getId(), flight.getOrigin(), flight.getDestination(),
                flight.getDepartureTime().format(FORMATTER), flight.getArrivalTime().format(FORMATTER),
                flight.getSeatsAvailable());
    }

    private static String createPassengerTableRow(int counter, Passenger passenger) {
        return String.format(PASSENGER_TABLE_COLUMNS,
                counter, passenger.fistName(), passenger.lastName());
    }

    private static String createReservationTableRow(int reservationId, int flightId) {
        return String.format(RESERVATION_TABLE_COLUMNS,
                TableString.RESERVATION, reservationId, TableString.FLIGHT, flightId);
    }

    public static String toFlightTable(Flight flight) {
        return toFlightTable(List.of(flight));
    }

    public static String toFlightTable(List<Flight> flights) {
        StringBuilder builder = new StringBuilder(FLIGHT_TABLE_SEPARATOR_ROW)
                .append("\n").append(getFlightTableHeader())
                .append("\n").append(FLIGHT_TABLE_SEPARATOR_ROW).append("\n");
        flights.forEach(flight -> builder.append(createFlightTableRow(flight)).append("\n"));
        return builder.append(FLIGHT_TABLE_SEPARATOR_ROW).toString();
    }

    public static String toPassengerTable(List<Passenger> passengers) {
        StringBuilder builder = new StringBuilder(PASSENGER_TABLE_SEPARATOR_ROW)
                .append("\n").append(getPassengerTableHeader())
                .append("\n").append(PASSENGER_TABLE_SEPARATOR_ROW).append("\n");
        for (int i = 0; i < passengers.size(); i++) {
            builder.append(createPassengerTableRow(i + 1, passengers.get(i))).append("\n");
        }
        return builder.append(PASSENGER_TABLE_SEPARATOR_ROW).toString();
    }

    public static String toReservationTable(Reservation reservation) {
        return RESERVATION_TABLE_SEPARATOR_ROW +
                "\n" + createReservationTableRow(reservation.id(), reservation.flightId()) +
                "\n" + RESERVATION_TABLE_SEPARATOR_ROW +
                "\n" + toPassengerTable(reservation.passengers());
    }

    private static String generateSeparatorRow(List<Integer> columnSymbols) {
        StringBuilder builder = new StringBuilder("+");
        columnSymbols.forEach(numberOfSymbols -> builder.append("-".repeat(numberOfSymbols)).append("+"));
        return builder.toString();
    }
}
