package HotelReservationSystem;
import java.io.*;
import java.util.*;

public class Hotel {
    private List<Room> rooms = new ArrayList<>();
    private List<Reservation> reservations = new ArrayList<>();
    private int reservationCounter = 1;
    private final String FILE_NAME = "reservations.txt";

    public Hotel() {
        // Predefined rooms
        rooms.add(new Room(101, "Standard"));
        rooms.add(new Room(102, "Standard"));
        rooms.add(new Room(201, "Deluxe"));
        rooms.add(new Room(202, "Deluxe"));
        rooms.add(new Room(301, "Suite"));
        loadReservations();
    }

    public void showAvailableRooms() {
        System.out.println("\nAvailable Rooms:");
        for (Room room : rooms) {
            if (!room.isBooked()) System.out.println(room);
        }
    }

    public Reservation makeReservation(String customerName, String category) {
        for (Room room : rooms) {
            if (!room.isBooked() && room.getCategory().equalsIgnoreCase(category)) {
                room.book();
                Reservation res = new Reservation(reservationCounter++, customerName, room);
                reservations.add(res);
                saveReservations();
                return res;
            }
        }
        return null;
    }

    public boolean cancelReservation(int reservationId) {
        Iterator<Reservation> iterator = reservations.iterator();
        while (iterator.hasNext()) {
            Reservation res = iterator.next();
            if (res.getReservationId() == reservationId) {
                res.getRoom().cancel();
                iterator.remove();
                saveReservations();
                return true;
            }
        }
        return false;
    }

    public void viewReservations() {
        if (reservations.isEmpty()) {
            System.out.println("No reservations found.");
            return;
        }
        for (Reservation res : reservations) {
            System.out.println(res);
        }
    }

    // File I/O
    private void saveReservations() {
        try (PrintWriter pw = new PrintWriter(new FileWriter(FILE_NAME))) {
            for (Reservation res : reservations) {
                pw.println(res.getReservationId() + "," + res.getCustomerName() + "," + res.getRoom().getRoomNumber());
            }
        } catch (IOException e) {
            System.out.println("Error saving reservations: " + e.getMessage());
        }
    }

    private void loadReservations() {
        File file = new File(FILE_NAME);
        if (!file.exists()) return;
        try (Scanner sc = new Scanner(file)) {
            while (sc.hasNextLine()) {
                String[] parts = sc.nextLine().split(",");
                if (parts.length == 3) {
                    int id = Integer.parseInt(parts[0]);
                    String name = parts[1];
                    int roomNum = Integer.parseInt(parts[2]);
                    Room room = findRoomByNumber(roomNum);
                    if (room != null) {
                        room.book();
                        reservations.add(new Reservation(id, name, room));
                        reservationCounter = Math.max(reservationCounter, id + 1);
                    }
                }
            }
        } catch (Exception e) {
            System.out.println("Error loading reservations: " + e.getMessage());
        }
    }

    private Room findRoomByNumber(int roomNum) {
        for (Room room : rooms) {
            if (room.getRoomNumber() == roomNum) return room;
        }
        return null;
    }
}
