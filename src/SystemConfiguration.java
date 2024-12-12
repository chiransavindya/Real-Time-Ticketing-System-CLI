public class SystemConfiguration {
    private int totalTickets;
    private double ticketReleaseRate;
    private double customerRetrievalRate;
    private int maxTicketCapacity;

    // Constructor
    public SystemConfiguration(int totalTickets, double ticketReleaseRate,
                               double customerRetrievalRate, int maxTicketCapacity) {
        // Input validation
        if (totalTickets <= 0 || ticketReleaseRate <= 0 ||
                customerRetrievalRate <= 0 || maxTicketCapacity <= 0) {
            throw new IllegalArgumentException("All configuration values must be positive");
        }

        this.totalTickets = totalTickets;
        this.ticketReleaseRate = ticketReleaseRate;
        this.customerRetrievalRate = customerRetrievalRate;
        this.maxTicketCapacity = maxTicketCapacity;
    }

    // Getters
    public int getTotalTickets() { return totalTickets; }
    public double getTicketReleaseRate() { return ticketReleaseRate; }
    public double getCustomerRetrievalRate() { return customerRetrievalRate; }
    public int getMaxTicketCapacity() { return maxTicketCapacity; }
}