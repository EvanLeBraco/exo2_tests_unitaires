import java.util.ArrayList;
import java.util.List;

public class FlightReservationSystem {
    private List<Flight> flights;
    
    public FlightReservationSystem() {
        this.flights = new ArrayList<>();
    }
    
    public List<Flight> getFlights() {
        return flights;
    }
    
    public void addFlight(Flight flight) {
        flights.add(flight);
    }
    
    public List<Flight> getAvailableFlights() {
        List<Flight> availableFlights = new ArrayList<>();
        
        for (Flight flight : flights) {
            if (flight.hasAvailableSeats()) {
                availableFlights.add(flight);
            }
        }
        
        return availableFlights;
    }
    
    public void reserveSeat(Flight flight, Passenger passenger) {
        if (flight.hasAvailableSeats()) {
            flight.reserveSeat();
            passenger.setFlight(flight);
        } else {
            System.out.println("Impossible de réserver un siège pour le vol " + flight.getFlightNumber());
        }
    }
}