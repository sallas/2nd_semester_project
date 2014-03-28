package dataSource;

import domain.FacilityBooking;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

public class FacilityBookingMapper extends AbstractMapper {

    public FacilityBookingMapper(Connection con) {
        super(con);
    }

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
    
    public boolean saveFacilityBooking(FacilityBooking fb){
        return true;
    }
    
    public List<FacilityBooking> getAllBookings(){
        return executeQueryAndGatherResults(FacilityBooking.class,
                "SELECT * FROM facility_booking", 
                "Fail in FacilityBookingMapper - getAllBookings",
                new String[]{"ID", "facilityID", "bookingDate", "timeslot"},
                new int[]{0, 0, 2, 0});
    }
    
    public List <FacilityBooking> getAllBookingsOfSpecificDate(Date date){
        return executeQueryAndGatherResults(FacilityBooking.class,
                "SELECT * FROM facility_booking WHERE booking_date = ?", 
                "Fail in FacilityBookingMapper - getAllBookings",
                new String[]{"ID", "facilityID", "bookingDate", "timeslot"},
                new int[]{0, 0, 2, 0}, date);
    }
}
