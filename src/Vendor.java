import java.util.UUID;
import java.util.concurrent.TimeUnit;

public class Vendor implements Runnable {
    private TicketPool ticketPool;
    private String vendorName;
    private double releaseRate;

    public Vendor(TicketPool ticketPool, String vendorName, double releaseRate) {
        this.ticketPool = ticketPool;
        this.vendorName = vendorName;
        this.releaseRate = releaseRate;
    }

    @Override
    public void run() {
        try {
            while (!Thread.currentThread().isInterrupted()) {
                // Create a new ticket
                Ticket ticket = new Ticket("Concert Event", 50.0,
                        UUID.randomUUID().toString());

                if (ticketPool.addTicket(ticket)) {
                    System.out.println(vendorName + " added a ticket");
                } else {
                    System.out.println(vendorName + " could not add ticket - pool full");
                }

                // Sleep based on release rate
                TimeUnit.MILLISECONDS.sleep((long)(1000 / releaseRate));
            }
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
            System.out.println(vendorName + " stopped");
        }
    }
}