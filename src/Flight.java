
public class Flight {

	private String flightNumber;
	private String departure;
	private String destination;
	private int capacity;
	private int reservedSeats;

	public Flight(String flightNumber, String departure, String destination, int capacity) {
		this.flightNumber = flightNumber;
		this.departure = departure;
		this.destination = destination;
		this.capacity = capacity;
		this.reservedSeats = 0;
	}

	public String getFlightNumber() {
		return flightNumber;
	}

	public String getDeparture() {
		return departure;
	}

	public String getDestination() {
		return destination;
	}

	public int getCapacity() {
		return capacity;
	}

	public int getReservedSeats() {
		return reservedSeats;
	}

	public boolean hasAvailableSeats() {
		return reservedSeats < capacity;
	}

	public void reserveSeat() {
		if (hasAvailableSeats()) {
			reservedSeats++;
			System.out.println("Si�ge r�serv� pour le vol " + flightNumber);
		} else {
			System.out.println("Aucun si�ge disponible pour le vol " + flightNumber);
		}
	}

}
