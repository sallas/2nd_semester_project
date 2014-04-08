package domain;

public class ReservationCustomer {

    private int reservationID;
    private int customerID;

    public ReservationCustomer() {
    }

    public ReservationCustomer(int reservationID, int customerID) {
        this.reservationID = reservationID;
        this.customerID = customerID;
    }

    public int getReservationID() {
        return reservationID;
    }

    public void setReservationID(int reservationID) {
        this.reservationID = reservationID;
    }

    public int getCustomerID() {
        return customerID;
    }

    public void setCustomerID(int customerID) {
        this.customerID = customerID;
    }

    @Override
    public String toString() {
        return "Reservation ID= " + reservationID
                + " | Customer ID= " + customerID;
    }
}
