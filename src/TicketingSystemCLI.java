import java.util.InputMismatchException;
import java.util.Scanner;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

public class TicketingSystemCLI {
    private static int getValidPositiveInteger(Scanner scanner, String prompt) {
        while (true) {
            try {
                System.out.print(prompt);
                int input = scanner.nextInt();

                if (input <= 0) {
                    System.out.println("Error: Please enter a positive number.");
                    continue;
                }

                return input;
            } catch (InputMismatchException e) {
                System.out.println("Error: Invalid input. Please enter a number.");
                scanner.nextLine(); // Clear the invalid input
            }
        }
    }

    private static double getValidPositiveDouble(Scanner scanner, String prompt) {
        while (true) {
            try {
                System.out.print(prompt);
                double input = scanner.nextDouble();

                if (input <= 0) {
                    System.out.println("Error: Please enter a positive number.");
                    continue;
                }

                return input;
            } catch (InputMismatchException e) {
                System.out.println("Error: Invalid input. Please enter a valid number.");
                scanner.nextLine(); // Clear the invalid input
            }
        }
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        try {
            System.out.println("=== Event Ticketing System Configuration ===");

            // Validated Inputs
            int totalTickets = getValidPositiveInteger(scanner,
                    "Enter Total Number of Tickets: ");

            double releaseRate = getValidPositiveDouble(scanner,
                    "Enter Ticket Release Rate (tickets per second): ");

            double retrievalRate = getValidPositiveDouble(scanner,
                    "Enter Customer Retrieval Rate (tickets per second): ");

            int maxCapacity = getValidPositiveInteger(scanner,
                    "Enter Maximum Ticket Capacity: ");

            // Additional Validation
            if (maxCapacity > totalTickets) {
                System.out.println("Warning: Maximum Capacity exceeds Total Tickets. " +
                        "Adjusting Maximum Capacity to Total Tickets.");
                maxCapacity = totalTickets;
            }

            // Create Configuration
            SystemConfiguration config = new SystemConfiguration(
                    totalTickets, releaseRate, retrievalRate, maxCapacity
            );

            // Create Ticket Pool
            TicketPool ticketPool = new TicketPool(config.getMaxTicketCapacity());

            // Create Thread Pool
            ExecutorService executorService = Executors.newFixedThreadPool(10);

            // Create and Start Vendors
            for (int i = 1; i <= 3; i++) {
                Vendor vendor = new Vendor(ticketPool, "Vendor-" + i, config.getTicketReleaseRate());
                executorService.submit(vendor);
            }

            // Create and Start Customers
            for (int i = 1; i <= 3; i++) {
                Customer customer = new Customer(ticketPool, "Customer-" + i, config.getCustomerRetrievalRate());
                executorService.submit(customer);
            }

            // Run for a specific duration
            System.out.println("\nSystem running... Press Enter to stop.");
            scanner.nextLine(); // Consume newline
            scanner.nextLine(); // Wait for Enter

            // Shutdown
            executorService.shutdownNow();
            executorService.awaitTermination(5, TimeUnit.SECONDS);

            System.out.println("Final Ticket Pool Count: " + ticketPool.getCurrentTicketCount());

        } catch (Exception e) {
            System.err.println("An unexpected error occurred: " + e.getMessage());
        } finally {
            scanner.close();
        }
    }
}