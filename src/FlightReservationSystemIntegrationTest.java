import org.junit.Assert;
import org.junit.Test;

public class FlightReservationSystemIntegrationTest {

    @Test
	public void testReserveSeat() {
		FlightReservationSystem flightRS1 = new FlightReservationSystem();
		//public Flight(String flightNumber, String departure, String destination, int capacity) 
		Flight flight1 = new Flight("A985B2D", "Nevers", "Paris", 85);
		Passenger passenger1 = new Passenger("La chienne de mère de gwen");
		
		flightRS1.addFlight(flight1);
		flightRS1.reserveSeat(flight1, passenger1);

        Assert.assertEquals("Le passager devrait avoir réservé le siège", flight1, passenger1.getFlight());
        Assert.assertEquals("Le nombre de sièges réservés devrait être mis à jour", 1, flight1.getReservedSeats());
	}
	
}