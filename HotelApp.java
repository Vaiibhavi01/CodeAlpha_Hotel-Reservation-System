package HotelReservationSystem;

import java.util.Scanner;

public class HotelApp {
    public static void main(String[] args) {
        Hotel hotel = new Hotel();
        Scanner sc = new Scanner(System.in);

        while (true) {
            System.out.println("\n==== Hotel Reservation System ====");
            System.out.println("1. View Available Rooms");
            System.out.println("2. Make Reservation");
            System.out.println("3. Cancel Reservation");
            System.out.println("4. View All Reservations");
            System.out.println("5. Exit");
            System.out.print("Choose option: ");

            int choice = sc.nextInt();
            sc.nextLine(); // consume newline

            switch (choice) {
                case 1:
                    hotel.showAvailableRooms();
                    break;
                case 2:
                    System.out.print("Enter your name: ");
                    String name = sc.nextLine();
                    System.out.print("Enter room category (Standard/Deluxe/Suite): ");
                    String category = sc.nextLine();
                    double price = category.equalsIgnoreCase("Suite") ? 3000 :
                                   category.equalsIgnoreCase("Deluxe") ? 2000 : 1000;

                    if (Payment.processPayment(name, price)) {
                        Reservation res = hotel.makeReservation(name, category);
                        if (res != null) {
                            System.out.println("Reservation successful!");
                            System.out.println(res);
                        } else {
                            System.out.println("No available rooms in this category.");
                        }
                    }
                    break;
                case 3:
                    System.out.print("Enter Reservation ID to cancel: ");
                    int resId = sc.nextInt();
                    if (hotel.cancelReservation(resId)) {
                        System.out.println("Reservation cancelled.");
                    } else {
                        System.out.println("Reservation not found.");
                    }
                    break;
                case 4:
                    hotel.viewReservations();
                    break;
                case 5:
                    System.out.println("Exiting system. Goodbye!");
                    sc.close();
                    return;
                default:
                    System.out.println("Invalid choice.");
            }
        }
    }
}

