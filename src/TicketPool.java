import java.util.Collections;
import java.util.List;
import java.util.ArrayList;
import java.util.concurrent.CopyOnWriteArrayList;

public class TicketPool {
    private List<Ticket> tickets;
    private final int maxCapacity;

    public TicketPool(int maxCapacity) {
        this.maxCapacity = maxCapacity;
        // Using CopyOnWriteArrayList for thread-safe operations
        this.tickets = new CopyOnWriteArrayList<>();
    }

    // Synchronized method to add tickets
    public synchronized boolean addTicket(Ticket ticket) {
        if (tickets.size() < maxCapacity) {
            tickets.add(ticket);
            return true;
        }
        return false;
    }

    // Synchronized method to remove ticket
    public synchronized Ticket removeTicket() {
        if (!tickets.isEmpty()) {
            return tickets.remove(0);
        }
        return null;
    }

    public int getCurrentTicketCount() {
        return tickets.size();
    }
}