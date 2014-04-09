package domain;

public class QueueEntry {
    private int ID;
    private int userID;
    private int facilityBookingID;

    public int getID() {
        return ID;
    }

    public int getUserID() {
        return userID;
    }

    public int getFacilityBookingID() {
        return facilityBookingID;
    }

    public void setID(int ID) {
        this.ID = ID;
    }

    public void setUserID(int userID) {
        this.userID = userID;
    }

    public void setFacilityBookingID(int facilityBookingID) {
        this.facilityBookingID = facilityBookingID;
    }

    public QueueEntry(int ID, int userID, int facilityBookingID) {
        this.ID = ID;
        this.userID = userID;
        this.facilityBookingID = facilityBookingID;
    }
    
    public QueueEntry() {}
}
