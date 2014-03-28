package dataSource;

import domain.FacilityBooking;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class FacilityBookingMapper {

    private final Connection con;

    public FacilityBookingMapper(Connection con) {
        this.con = con;
    }

    /*
    *   Returns true if wanted booking is available
    */
    public boolean checkAvailableFacilityBooking(FacilityBooking fb) {
        boolean unavailable = true;

        // This select tries to get a reservation that would conflict with 
        // the reservation we are trying to put into the database
        String SQLString = "select * from facility_booking where"
                + " facility_id = ? AND booking_date = ? AND timeslot = ?";
        PreparedStatement statement = null;

        try {
            statement = con.prepareStatement(SQLString);
            statement.setInt(1, fb.getFacilityID());
            statement.setDate(2, fb.getBookingDate());
            statement.setInt(3, fb.getTimeslot());
            ResultSet rs = statement.executeQuery();

            unavailable = rs.next();  // rs would return a conflicting reservation or nothing

        } catch (SQLException e) {
            System.out.println("Fail in FacilityBookingMapper - checkAvailableFacilityBooking");
            System.out.println(e.getMessage());
        } finally {
            try {
                if (statement != null) {
                    statement.close();
                }
            } catch (SQLException e) {
                System.out.println("Fail in FacilityBookingMapper - checkAvailableFacilityBooking");
                System.out.println(e.getMessage());
            }
        }

        return !unavailable;  //returns the opposite of unavaiable
    }
}
