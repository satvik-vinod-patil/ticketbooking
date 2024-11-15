import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.time.LocalDate;
import java.util.List;

public class GUIManager extends JFrame {
    private JComboBox<String> sourceBox, destinationBox;
    private JTextField passengerNameField, dateField, seatNumberField;
    private JTextArea bookingsArea;
    private BusBookingSystem bookingSystem;

    public GUIManager() {
        bookingSystem = new BusBookingSystem();

        setTitle("Bus Ticket Booking System");
        setSize(500, 400);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new GridLayout(7, 2));

        add(new JLabel("Source:"));
        sourceBox = new JComboBox<>(new String[]{"Mumbai", "Pune", "Nagpur"});
        add(sourceBox);

        add(new JLabel("Destination:"));
        destinationBox = new JComboBox<>(new String[]{"Mumbai", "Pune", "Nagpur"});
        add(destinationBox);

        add(new JLabel("Passenger Name:"));
        passengerNameField = new JTextField();
        add(passengerNameField);

        add(new JLabel("Journey Date (YYYY-MM-DD):"));
        dateField = new JTextField();
        add(dateField);

        add(new JLabel("Seat Number:"));
        seatNumberField = new JTextField();
        add(seatNumberField);

        JButton bookButton = new JButton("Book Ticket");
        bookButton.addActionListener(this::bookTicket);
        add(bookButton);

        JButton viewButton = new JButton("View Bookings");
        viewButton.addActionListener(this::viewBookings);
        add(viewButton);

        JButton deleteButton = new JButton("Delete Completed");
        deleteButton.addActionListener(e -> bookingSystem.deleteCompletedJourneys());
        add(deleteButton);

        bookingsArea = new JTextArea();
        add(new JScrollPane(bookingsArea));

        setVisible(true);
    }

    private void bookTicket(ActionEvent e) {
        try {
            int busNumber = sourceBox.getSelectedIndex() + destinationBox.getSelectedIndex() + 1;
            String source = (String) sourceBox.getSelectedItem();
            String destination = (String) destinationBox.getSelectedItem();
            String passengerName = passengerNameField.getText();
            LocalDate journeyDate = LocalDate.parse(dateField.getText());
            int seatNumber = Integer.parseInt(seatNumberField.getText());

            boolean success = bookingSystem.bookTicket(busNumber, source, destination, passengerName, journeyDate, seatNumber);
            if (success) {
                JOptionPane.showMessageDialog(this, "Ticket Booked Successfully!");
            } else {
                JOptionPane.showMessageDialog(this, "Booking Failed.");
            }
        } catch (Exception ex) {
            ex.printStackTrace();
            JOptionPane.showMessageDialog(this, "Error in booking.");
        }
    }

    private void viewBookings(ActionEvent e) {
        bookingsArea.setText("");
        List<Ticket> tickets = bookingSystem.getBookings();
        for (Ticket ticket : tickets) {
            bookingsArea.append("Bus No: " + ticket.getBusNumber() +
                    ", Source: " + ticket.getSource() +
                    ", Destination: " + ticket.getDestination() +
                    ", Passenger: " + ticket.getPassengerName() +
                    ", Date: " + ticket.getJourneyDate() +
                    ", Seat: " + ticket.getSeatNumber() + "\n");
        }
    }

    public static void main(String[] args) {
        new GUIManager();
    }
}
