import java.util.concurrent.TimeUnit;

public class Customer implements Runnable {
    private TicketPool ticketPool;
    private String customerName;
    private double retrievalRate;

    public Customer(TicketPool ticketPool, String customerName, double retrievalRate) {
        this.ticketPool = ticketPool;
        this.customerName = customerName;
        this.retrievalRate = retrievalRate;
    }

    @Override
    public void run() {
        try {
            while (!Thread.currentThread().isInterrupted()) {
                Ticket ticket = ticketPool.removeTicket();

                if (ticket != null) {
                    System.out.println(customerName + " purchased a ticket: " + ticket);
                } else {
                    System.out.println(customerName + " found no tickets available");
                }

                // Sleep based on retrieval rate
                TimeUnit.MILLISECONDS.sleep((long)(1000 / retrievalRate));
            }
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
            System.out.println(customerName + " stopped");
        }
    }
}