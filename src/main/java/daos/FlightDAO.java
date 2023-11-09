package daos;
import models.Flight;
import java.util.List;

public interface FlightDAO {
        List<Flight> getAllFlights();
        Flight getFlightById(int id);
        void addFlight(Flight flight);
        void addListFlight (List<Flight> flightList);
        void deleteFlightById(int id);
}
