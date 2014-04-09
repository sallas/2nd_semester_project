package dataSource;

import domain.ReservationCustomer;
import java.sql.Connection;
import java.util.List;

public class ReservationCustomerMapper extends AbstractMapper {

    public ReservationCustomerMapper(Connection con) {
        super(con, "reservation_customer", ReservationCustomer.class);
    }

    public boolean saveReservationCustomer(ReservationCustomer rc) {
        int result = executeSQLInsert(
                "INSERT INTO reservation_customer VALUES (?, ?)",
                "Fail in ReservationCustomerMapper - saveReservationCustomer",
                rc.getReservationID(), rc.getCustomerID());
        return result != 0;
    }

    public List<ReservationCustomer> search(String columnName, Object variable) {
        return generalSearch(columnName, "Fail in ReservationCustomerMapper - saveReservationCustomer", variable);
    }

    public boolean removeByReservationID(int i) {
        return generalDelete("reservation_ID", "Fail in ReservationCustomerMapper"
                + " - removeByReservationID ", i);
    }
}
