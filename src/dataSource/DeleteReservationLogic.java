package dataSource;

import java.sql.Connection;
import java.sql.SQLException;

public class DeleteReservationLogic {
    /*
     * Transaction for deleting a reservation from the database
     * Returns true if reservation was removed.
     * Locks the reservation table for a split second for safety
     */

    public static boolean deleteReservation(int ID,
            Connection connection,
            ReservationMapperInterface reservationMapper,
            HotelUserMapperInterface hotelUserMapper,
            QueueMapperInterface queueMapper,
            FacilityBookingMapperInterface facilityBookingMapper,
            ReservationCustomerMapper resCustomerMapper) {

        boolean status = true;
        try {
            //=== system transaction - starts
            connection.setAutoCommit(false);
            reservationMapper.lockReservationTable();
            
            queueMapper.deleteQueueEntryByReservationID(ID);
            facilityBookingMapper.deleteFacilityBookingByReservationID(ID);
            hotelUserMapper.removeHotelUserByReservationID(ID);
            reservationMapper.removeUnpaidReservation(ID);
            resCustomerMapper.removeByReservationID(ID);
            status = reservationMapper.removeReservation(ID);
            
            if (!status){
                connection.rollback();
                return status;
            }
            //=== system transaction - ends with success
            connection.commit();
        } catch (Exception e) {
            //=== system transaction - ends with roll back
            try {
                connection.rollback();
            } catch (SQLException e1) {
                e1.printStackTrace();
            }
            status = false;
        }
        return status;
    }
}
