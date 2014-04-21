package dataSource;

import domain.FacilityBooking;
import java.sql.Connection;
import java.sql.Date;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

public class FacilityBookingMapper extends AbstractMapper implements FacilityBookingMapperInterface {

    public FacilityBookingMapper(Connection con) {
        super(con, "facility_booking", FacilityBooking.class);
    }

    /*
     *   Returns true if wanted booking is available
     */
    @Override
    public boolean checkAvailableFacilityBooking(FacilityBooking fb) {
        boolean unavailable = true;

        // This select tries to get a reservation that would conflict with 
        // the reservation we are trying to put into the database
        ResultSet rs = executeSQLQuery("select * from facility_booking where"
                + " facility_id = ? AND booking_date = ? AND timeslot = ?",
                "Fail in FacilityBookingMapper - checkAvailableFacilityBooking",
                fb.getFacilityID(), fb.getBookingDate(), fb.getTimeslot());
        try {
            unavailable = rs.next();  // rs would return a conflicting reservation or nothing
            if (rs != null) {
                rs.close();
            }
        } catch (SQLException e) {
            System.out.println("Fail in FacilityBookingMapper - checkAvailableFacilityBooking");
            System.out.println(e.getMessage());
        }
        return !unavailable;  //returns the opposite of unavaiable
    }

    @Override
    public boolean saveFacilityBooking(FacilityBooking fb) {
        int seqNum = getSequenceNumber("SELECT facility_bookingseq.nextval"
                + " FROM dual",
                "Fail in FacilityBookingMapper - saveFacilityBooking");
        fb.setID(seqNum);
        int result = executeSQLInsert(
                "INSERT INTO facility_booking VALUES (?, ?, ?, ?, ?, ?)",
                "Fail in FacilityBookingMapper - saveFacilityBooking",
                fb.getID(), fb.getFacilityID(), fb.getBookingDate(),
                fb.getTimeslot(), fb.getUserID(), 
                fb.isIsBookedByOriginalUser() ? 1 : 0);
        return result != 0;
    }

    @Override
    public List<FacilityBooking> getAllBookings() {
        return executeQueryAndGatherResults(FacilityBooking.class,
                "SELECT * FROM facility_booking",
                "Fail in FacilityBookingMapper - getAllBookings");
    }

    @Override
    public List<FacilityBooking> getAllBookingsOfSpecificDate(Date date) {
        return generalSearch("booking_date",
                "Fail in FacilityBookingMapper - "
                + "getAllBookingsOfSpecificDate", date);
    }
    
    @Override
    public List<FacilityBooking> getAllBookingsOfSpecificID(int id) {
        return generalSearch("id",
                "Fail in FacilityBookingMapper - "
                + "getAllBookingsOfSpecificID", id);
    }

    /*
     * Returns all faciltiy bookings made by user with id userID and on the
     * specified date
     */
    @Override
    public List<FacilityBooking> getAllBookingsOfSpecificDateAndUser(
            Date date, int userID) {
        return executeQueryAndGatherResults(FacilityBooking.class,
                "SELECT * FROM facility_booking WHERE booking_date = ? AND user_id = ?",
                "Fail in FacilityBookingMapper - getAllBookings", date, userID);
    }

    /*
     * Returns all faciltiy bookings made by user with id userID and on the
     * spefified date and timeslot
     */
    @Override
    public List<FacilityBooking> getAllBookingsOfSpecificDateTimeslotUser(
            Date date, int userID, int timeslot) {
        return executeQueryAndGatherResults(FacilityBooking.class,
                "SELECT * FROM facility_booking WHERE booking_date = ?"
                + " AND user_id = ? AND timeslot = ?",
                "Fail in FacilityBookingMapper - getAllBookings",
                date, userID, timeslot);
    }

    /*
     * Returns all faciltiy bookings made for a f id userID and on the
     
     */
    @Override
    public List<FacilityBooking> getAllBookingsOfSpecificDateTimeslotFacility(
            Date date, int timeslot, int facilityID) {
        return executeQueryAndGatherResults(FacilityBooking.class,
                "SELECT * FROM facility_booking WHERE booking_date = ?"
                + " AND timeslot = ? AND facility_id = ?",
                "Fail in FacilityBookingMapper - getAllBookings",
                date, timeslot, facilityID);
    }

    @Override
    public boolean removeFacilityBooking(int ID) {
        return executeSQLInsert(
                "DELETE FROM facility_booking WHERE id = ?",
                "Fail in FacilityBookingMapper - removeFacilityBooking",
                ID) == 1;
    }

    @Override
    public List<FacilityBooking> getAllFacilityBookingOfSpecificUser(int ID) {
        return generalSearch("user_id", "Fail in FacilityBookingMapper -"
                + " getAllFacilityBookingOfSpecificUser ", ID);
    }

    @Override
    public List<FacilityBooking> search(Object variable, String columnName) {
        return generalSearch(columnName,
                "Fail in FacilityBookingMapper - search ", variable);
    }

    @Override
    public boolean updateFacilityBookingUserID(int bookingID, int userID) {
        return executeSQLInsert(
                "UPDATE facility_booking SET user_id = ?, IS_ORIGINAL_USER = 0 WHERE id = ?",
                "Fail in FacilityBookingMapper - updateFacilityBookingUserID",
                userID, bookingID) == 1;
    }

    @Override
    public boolean deleteFacilityBookingByReservationID(int ID) {
        return 0 != executeSQLInsert(
                "delete from facility_booking where "
                + "user_ID = (select user_id from hotel_user"
                + " where reservation_id = ?)",
                "Fail in FacilityBookingMapper - deleteFacilityBookingByReservationID",
                ID);
    }
}
