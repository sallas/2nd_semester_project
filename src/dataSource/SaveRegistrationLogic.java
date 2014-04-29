package dataSource;

import domain.Customer;
import domain.Reservation;
import domain.ReservationCustomer;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/*
 *   Helper class for the controller class to make it more readable
 */
public class SaveRegistrationLogic {

    /*
     * Transaction for saving a reservation into the database
     * Returns true if reservation was saved, throws an error if reservation
     * conflict with another reservation.
     * Locks the reservation table for a split second so no double booking will happen
     */
    public static boolean saveReservationInformation(Reservation r,
            List<Customer> c, Connection connection,
            ReservationMapperInterface reservationMapper,
            CustomerMapperInterface customerMapper,
            ReservationCustomerMapper resCustomerMapper) {

        boolean status = true;
        try {
            //=== system transaction - starts
            connection.setAutoCommit(false);
            reservationMapper.lockReservationTable();

            status = status && reservationMapper.checkAvailableReservation(r);
            
            int customerID = customerMapper.saveNewCustomer(c.get(0));
            List<ReservationCustomer> resCus = new ArrayList<>();
            resCus.add(new ReservationCustomer(-1, customerID));
            status = customerID != -1 && status;
            c.remove(0);
            for (Customer customer : c) {
                int ID = customerMapper.saveNewCustomer(customer);
                status = ID != -1 && status;
                resCus.add(new ReservationCustomer(-1, ID));
            }
            r.setCustomerID(customerID);
            int resID = reservationMapper.saveReservation(r);
            status = resID != -1 && status;
            for (ReservationCustomer rc : resCus) {
                rc.setReservationID(resID);
                status = status && resCustomerMapper.saveReservationCustomer(rc);
            }
            
            if (!status) {
                throw new Exception("Save Reservation Transaction aborted");
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
