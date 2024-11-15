import java.time.LocalDate;
import java.util.List;

public class BusBookingSystem {
    private FileManager fileManager;

    public BusBookingSystem() {
        fileManager = new FileManager("tickets.txt");
    }

    public boolean bookTicket(int busNumber, String source, String destination, String passengerName, LocalDate journeyDate, int seatNumber) {
        Ticket ticket = new Ticket(busNumber, source, destination, passengerName, journeyDate, seatNumber);
        return fileManager.saveTicket(ticket);
    }

    public List<Ticket> getBookings() {
        return fileManager.loadTickets();
    }

    public void deleteCompletedJourneys() {
        fileManager.deleteOldTickets(LocalDate.now());
    }
}
