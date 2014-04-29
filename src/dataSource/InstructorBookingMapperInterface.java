package dataSource;

import domain.InstructorBooking;
import java.sql.Date;
import java.util.List;

public interface InstructorBookingMapperInterface {
    
    List<InstructorBooking> getInstructorBookingByUserID(int userID);
    
    List<InstructorBooking> getInstructorBookingByUserIDAndDate(
            int userID, Date date);
    
    List<InstructorBooking> search(Object variable, String columnName);
}
