package dataSource;

import domain.InstructorBooking;
import java.sql.Connection;
import java.sql.Date;
import java.util.List;

public class InstructorBookingMapper extends AbstractMapper {

    public InstructorBookingMapper(Connection con) {
        super(con, "Instructor_Booking", InstructorBooking.class);
    }

    public List<InstructorBooking> getInstructorBookingByUserID(int userID) {
        return executeQueryAndGatherResults(
                InstructorBooking.class,
                "SELECT * FROM Instructor_Booking "
                + "WHERE instructor_id = (SELECT id FROM instructor "
                + "WHERE user_id = ?)",
                "Fail in InstructorBookingMapper - "
                + "getInstructorBookingByUserID ",
                userID);
    }

    public List<InstructorBooking> getInstructorBookingByUserIDAndDate(
            int userID, Date date) {
        return executeQueryAndGatherResults(
                InstructorBooking.class,
                "SELECT * FROM Instructor_Booking "
                + "WHERE booked_date = ? AND instructor_id = "
                + "(SELECT id FROM instructor WHERE user_id = ?)",
                "Fail in InstructorBookingMapper - "
                + "getInstructorBookingByUserID ",
                date, userID);
    }

    public List<InstructorBooking> search(Object variable, String columnName) {
        return generalSearch(columnName,
                "Fail in InstructorBookingMapper - search ", variable);
    }

}
