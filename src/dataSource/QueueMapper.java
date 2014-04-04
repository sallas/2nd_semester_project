package dataSource;

import domain.QueueEntry;
import java.sql.Connection;
import java.util.ArrayList;
import java.util.List;

public class QueueMapper extends AbstractMapper implements QueueMapperInterface{

    public QueueMapper(Connection con) {
        super(con);
    }
    
    /*
     * Get all queue entries for specific booking.
     */
    @Override
    public List<QueueEntry> getQueueForSpecificBooking(int booking_id){
        return executeQueryAndGatherResults(QueueEntry.class,
                "SELECT * FROM queue_facility WHERE facility_booking_id = ?",
                "Fail in QueueMapper - getQueueForSpecificBooking",
                new String[]{"ID", "userID", "facilityBookingID"},
                new int[]{0, 0, 0}, booking_id);
    }
    
    /*
     * Get all queue etries for specific user.
     */
    @Override
    public List<QueueEntry> getQueueForSpecificUser(int userID){
        return executeQueryAndGatherResults(QueueEntry.class,
                "SELECT * FROM queue_facility WHERE user_id = ?",
                "Fail in QueueMapper - getQueueForSpecificUser",
                new String[]{"ID", "userID", "facilityBookingID"},
                new int[]{0, 0, 0}, userID);
    }
    
    /*
     * Save a queue entry to db.
     */
    @Override
    public boolean saveQueueEntry(QueueEntry entry) {
        ArrayList<QueueEntry> seq = executeQueryAndGatherResults(
                QueueEntry.class,
                "SELECT queue_facilityseq.nextval "
                + "FROM dual",
                "Fail in QueueMapper - saveQueueEntry - nextval",
                new String[]{"ID"}, new int[]{0});
        entry.setID(seq.get(0).getID());
        int result = executeSQLInsert(
                "INSERT INTO queue_facility VALUES (?, ?, ?)",
                "Fail in QueueMapper - saveQueueEntry",
                entry.getID(), entry.getUserID(), entry.getFacilityBookingID());
        return result != 0;
    }
    
    /*
     * Delete entry from queue by ids of booking and user.
     */
    @Override
    public boolean deleteQueueEntryForSpecificID(int bookingID, int userID) {
        int result = executeSQLInsert(
                "DELETE FROM queue_facility" +
                " WHERE facility_booking_id = ? AND user_id = ?",
                "Fail in QueueMapper - deleteQueueEntryForSpecificID",
                bookingID, userID);
        return result != 0;
    }
    
    @Override
    public boolean deleteQueueEntryByReservationID(int ID){
        return 0 != executeSQLInsert(
                "delete from queue_facility "
                        + "where user_ID = "
                        + "(select user_id from hotel_user"
                        + " where reservation_id = ?)",
                "Fail in QueueMapper - delete QueueEntryByReservationID", 
                ID);
    }
}
