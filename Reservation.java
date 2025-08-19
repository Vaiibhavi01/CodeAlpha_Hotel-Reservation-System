package HotelReservationSystem;
public class Reservation {
    private int reservationId;
    private String customerName;
    private Room room;

    public Reservation(int reservationId, String customerName, Room room) {
        this.reservationId = reservationId;
        this.customerName = customerName;
        this.room = room;
    }

    public int getReservationId() { return reservationId; }
    public String getCustomerName() { return customerName; }
    public Room getRoom() { return room; }

    @Override
    public String toString() {
        return "Reservation ID: " + reservationId + " | Name: " + customerName + " | " + room.toString();
    }
}

