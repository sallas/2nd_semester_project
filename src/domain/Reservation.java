package domain;

import java.sql.Date;

public class Reservation {

    private int ID;
    private int roomID;
    private int customerID;
    private Date checkinDate;
    private Date departureDate;

    public Reservation(int ID, int roomID, int customerID, Date checkinDate, Date departureDate) {
        this.ID = ID;
        this.roomID = roomID;
        this.customerID = customerID;
        this.checkinDate = checkinDate;
        this.departureDate = departureDate;
    }

    public int getID() {
        return ID;
    }

    public void setID(int ID) {
        this.ID = ID;
    }

    public int getRoomID() {
        return roomID;
    }

    public void setRoomID(int roomID) {
        this.roomID = roomID;
    }

    public int getCustomerID() {
        return customerID;
    }

    public void setCustomerID(int customerID) {
        this.customerID = customerID;
    }

    public Date getCheckinDate() {
        return checkinDate;
    }

    public void setCheckinDate(Date checkinDate) {
        this.checkinDate = checkinDate;
    }

    public Date getDepartureDate() {
        return departureDate;
    }

    public void setDepartureDate(Date departureDate) {
        this.departureDate = departureDate;
    }

    @Override
    public String toString() {
        return "Reservation: " + "ID = " + ID + ", roomID = " + roomID
                + ", customerID = " + customerID + ", checkinDate = "
                + checkinDate + ", numberNights = " + departureDate + '.';
    }
}
