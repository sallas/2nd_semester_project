package dataSource;

import domain.InstructorBooking;
import java.sql.Connection;
import java.util.List;

public class InstructorBookingMapper extends AbstractMapper {

    public InstructorBookingMapper(Connection con) {
        super(con);
    }

    public List<InstructorBooking> getInstructorBookingByUserID(int userID) {
        return executeQueryAndGatherResults(
                InstructorBooking.class,
                "SELECT * FROM Instructor_Booking "
                + "WHERE instructor_id = (SELECT id FROM instructor "
                + "WHERE user_id = ?)",
                "Fail in InstructorBookingMapper - search ",
                userID);
    }

    public List<InstructorBooking> search(Object variable, String columnName) {
        return executeQueryAndGatherResults(
                InstructorBooking.class,
                "SELECT * FROM Instructor_Booking "
                + "WHERE " + columnName + " = ?",
                "Fail in InstructorBookingMapper - search ",
                variable);
    }

}
