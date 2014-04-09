package dataSource;

import domain.QueueEntry;
import java.util.List;

public interface QueueMapperInterface {

    /*
     * Delete entry from queue by ids of booking and user.
     */
    boolean deleteQueueEntryForSpecificID(int bookingID, int userID);

    /*
     * Get all queue entries for specific booking.
     */
    List<QueueEntry> getQueueForSpecificBooking(int booking_id);

    /*
     * Get all queue etries for specific user.
     */
    List<QueueEntry> getQueueForSpecificUser(int userID);

    /*
     * Save a queue entry to db.
     */
    boolean saveQueueEntry(QueueEntry entry);
    
    boolean deleteQueueEntryByReservationID(int ID);
}
