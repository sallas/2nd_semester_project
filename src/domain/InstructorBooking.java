package domain;

import java.sql.Date;

public class InstructorBooking {

    private int id;
    private int facilityID;
    private int instructorID;
    private Date bookedDate;
    private int timeslot;

    public InstructorBooking() {
    }

    public InstructorBooking(int id, int facilityID, int instructorID, Date bookedDate, int timeslot) {
        this.id = id;
        this.facilityID = facilityID;
        this.instructorID = instructorID;
        this.bookedDate = bookedDate;
        this.timeslot = timeslot;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getFacilityID() {
        return facilityID;
    }

    public void setFacilityID(int facilityID) {
        this.facilityID = facilityID;
    }

    public int getInstructorID() {
        return instructorID;
    }

    public void setInstructorID(int instructorID) {
        this.instructorID = instructorID;
    }

    public Date getBookedDate() {
        return bookedDate;
    }

    public void setBookedDate(Date bookedDate) {
        this.bookedDate = bookedDate;
    }

    public int getTimeslot() {
        return timeslot;
    }

    public void setTimeslot(int timeslot) {
        this.timeslot = timeslot;
    }

    @Override
    public String toString() {
        return "InstructorBooking id= " + id + " | facilityID= "
                + facilityID + " | instructorID=" + instructorID
                + " | bookedDate= " + bookedDate + " | timeslot= " + timeslot;
    }

}
