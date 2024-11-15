import java.time.LocalDate;

public class Ticket {
    private int busNumber;
    private String source;
    private String destination;
    private String passengerName;
    private LocalDate journeyDate;
    private int seatNumber;

    // Constructor
    public Ticket(int busNumber, String source, String destination, String passengerName, LocalDate journeyDate, int seatNumber) {
        this.busNumber = busNumber;
        this.source = source;
        this.destination = destination;
        this.passengerName = passengerName;
        this.journeyDate = journeyDate;
        this.seatNumber = seatNumber;
    }

    // Getters and setters
    public int getBusNumber() {
        return busNumber;
    }

    public String getSource() {
        return source;
    }

    public String getDestination() {
        return destination;
    }

    public String getPassengerName() {
        return passengerName;
    }

    public LocalDate getJourneyDate() {
        return journeyDate;
    }

    public int getSeatNumber() {
        return seatNumber;
    }

    // CSV conversion methods
    public String toCSV() {
        return busNumber + "," + source + "," + destination + "," + passengerName + "," + journeyDate + "," + seatNumber;
    }

    public static Ticket fromCSV(String csv) {
        String[] parts = csv.split(",");
        return new Ticket(
                Integer.parseInt(parts[0]),
                parts[1],
                parts[2],
                parts[3],
                LocalDate.parse(parts[4]),
                Integer.parseInt(parts[5])
        );
    }
}
