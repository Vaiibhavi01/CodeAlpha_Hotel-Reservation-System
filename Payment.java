package HotelReservationSystem;
import java.util.Random;

public class Payment {
    public static boolean processPayment(String customerName, double amount) {
        System.out.println("Processing payment of $" + amount + " for " + customerName + "...");
        // Simple simulation
        boolean success = new Random().nextBoolean();
        if (success) {
            System.out.println("Payment Successful!");
        } else {
            System.out.println("Payment Failed! Try again.");
        }
        return success;
    }
}

