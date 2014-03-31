package dataSource;

import domain.FacilityBooking;
import domain.QueueEntry;
import java.sql.Connection;
import java.util.ArrayList;
import java.util.List;

public class QueueMapper extends AbstractMapper{

    public QueueMapper(Connection con) {
        super(con);
    }
    
    /*
     * Get all queue entries for specific booking.
     */
    public List<QueueEntry> getQueueForSpecificBooking(int booking_id){
        return executeQueryAndGatherResults(QueueEntry.class,
                "SELECT * FROM queue_facility WHERE booking_id = ?",
                "Fail in QueueMapper - getQueueForSpecificBooking",
                new String[]{"ID", "userID", "facilityBookingID"},
                new int[]{0, 0, 0}, booking_id);
    }
    
    /*
     * Get all queue etries for specific user.
     */
    public List<QueueEntry> getQueueForSpecificUser(int user_id){
        return executeQueryAndGatherResults(QueueEntry.class,
                "SELECT * FROM queue_facility WHERE user_id = ?",
                "Fail in QueueMapper - getQueueForSpecificUser",
                new String[]{"ID", "userID", "facilityBookingID"},
                new int[]{0, 0, 0}, user_id);
    }
    
    /*
     * Save a queue entry to db.
     */
    public boolean saveQueueEntry(QueueEntry entry){
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
}
