import org.junit.Assert;
import org.junit.Test;

public class FlightReservationSystemIntegrationTest {

    @Test
	public void testReserveSeat() {
		FlightReservationSystem flightRS1 = new FlightReservationSystem();
		//public Flight(String flightNumber, String departure, String destination, int capacity) 
		Flight flight1 = new Flight("A985B2D", "Nevers", "Paris", 85);
		Passenger passenger1 = new Passenger("La chienne de m�re de gwen");
		
		flightRS1.addFlight(flight1);
		flightRS1.reserveSeat(flight1, passenger1);

        Assert.assertEquals("Le passager devrait avoir r�serv� le si�ge", flight1, passenger1.getFlight());
        Assert.assertEquals("Le nombre de si�ges r�serv�s devrait �tre mis � jour", 1, flight1.getReservedSeats());
	}
	
}