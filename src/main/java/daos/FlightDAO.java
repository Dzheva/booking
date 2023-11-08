package daos;
import models.Flight;
import java.util.List;

public interface FlightDAO {
        List<Flight> getAllFlights();
        Flight getFlightById(int id);
        void addFlight(Flight flight);
        void deleteFlightById(int id);
        void generateAndSaveFlights(int quantity);
}
