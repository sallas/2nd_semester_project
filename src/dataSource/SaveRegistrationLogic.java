package dataSource;

import domain.Customer;
import domain.Reservation;
import java.sql.Connection;
import java.sql.SQLException;

public class SaveRegistrationLogic {

    public static boolean saveReservationInformation(Reservation r,
            Customer c, Connection connection,
            ReservationMapperInterface reservationMapper,
            CustomerMapperInterface customerMapper) {

        boolean status = true;
        try {
            //=== system transaction - starts
            connection.setAutoCommit(false);
            reservationMapper.lockReservationTable();

            status = status && reservationMapper.checkAvailableReservation(r);
            int customerID = customerMapper.saveNewCustomer(c);
            if (customerID == -1) {
                status = false;
            }
            r.setCustomerID(customerID);
            status = status && reservationMapper.saveReservation(r);

            if (!status) {
                throw new Exception("Save Reservation Transaction aborted");
            }
            //=== system transaction - ends with success
            connection.commit();
        }
        catch (Exception e) {
            //=== system transaction - ends with roll back
            try {
                connection.rollback();
            }
            catch (SQLException e1) {
                e1.printStackTrace();
            }
            status = false;
        }
        return status;
    }
}
