package domain;

import java.sql.Date;

public class FacilityBooking {

    private int ID;
    private int facilityID;
    private Date bookingDate;
    private int timeslot;
    private int userID;
    private boolean isBookedByOriginalUser = true;

    public FacilityBooking(int ID, int facilityID, Date bookingDate, int timeslot, int userID, boolean isBookedByOriginalUser) {
        this.ID = ID;
        this.facilityID = facilityID;
        this.bookingDate = bookingDate;
        this.timeslot = timeslot;
        this.userID = userID;
        this.isBookedByOriginalUser = isBookedByOriginalUser;
    }

    public FacilityBooking() {
        bookingDate = null;
    }

    public int getID() {
        return ID;
    }

    public void setID(int ID) {
        this.ID = ID;
    }

    public int getFacilityID() {
        return facilityID;
    }

    public void setFacilityID(int facilityID) {
        this.facilityID = facilityID;
    }

    public Date getBookingDate() {
        return bookingDate;
    }

    public void setBookingDate(Date bookingDate) {
        this.bookingDate = bookingDate;
    }

    public int getTimeslot() {
        return timeslot;
    }

    public void setTimeslot(int timeslot) {
        this.timeslot = timeslot;
    }

    public int getUserID() {
        return userID;
    }

    public void setUserID(int userID) {
        this.userID = userID;
    }

    public boolean isIsBookedByOriginalUser() {
        return isBookedByOriginalUser;
    }

    public void setIsBookedByOriginalUser(boolean isBookedByOriginalUser) {
        this.isBookedByOriginalUser = isBookedByOriginalUser;
    }
    
    
    @Override
    public String toString() {
        return "Sports Booking " + "ID= " + ID + " | Facility ID= " + facilityID
                + " | Booking Date= " + bookingDate + " | Timeslot= " + timeslot
                + " | User ID= " + userID;
    }

    
    
}
