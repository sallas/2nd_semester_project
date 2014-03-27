package domain;

import java.sql.Date;

public class FacilityBooking {
    private int ID;
    private int facilityID;
    private Date bookingDate;
    private int timeslot;

    public FacilityBooking(int ID, int facilityID, Date bookingDate, int timeslot) {
        this.ID = ID;
        this.facilityID = facilityID;
        this.bookingDate = bookingDate;
        this.timeslot = timeslot;
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
    
    
}
