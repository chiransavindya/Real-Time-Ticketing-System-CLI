public class Ticket {
    private String eventName;
    private double price;
    private String uniqueId;

    public Ticket(String eventName, double price, String uniqueId) {
        this.eventName = eventName;
        this.price = price;
        this.uniqueId = uniqueId;
    }

    @Override
    public String toString() {
        return "Ticket{" +
                "eventName='" + eventName + '\'' +
                ", price=" + price +
                ", uniqueId='" + uniqueId + '\'' +
                '}';
    }
}