import java.io.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class FileManager {
    private final File file;

    public FileManager(String filename) {
        file = new File(filename);
        try {
            if (!file.exists()) file.createNewFile();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public boolean saveTicket(Ticket ticket) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(file, true))) {
            writer.write(ticket.toCSV() + "\n");
            return true;
        } catch (IOException e) {
            e.printStackTrace();
            return false;
        }
    }

    public List<Ticket> loadTickets() {
        List<Ticket> tickets = new ArrayList<>();
        try (BufferedReader reader = new BufferedReader(new FileReader(file))) {
            String line;
            while ((line = reader.readLine()) != null) {
                tickets.add(Ticket.fromCSV(line));
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return tickets;
    }

    public void deleteOldTickets(LocalDate currentDate) {
        List<Ticket> tickets = loadTickets();
        tickets.removeIf(ticket -> ticket.getJourneyDate().isBefore(currentDate));
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(file))) {
            for (Ticket ticket : tickets) {
                writer.write(ticket.toCSV() + "\n");
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
