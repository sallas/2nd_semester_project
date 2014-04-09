package dataSource;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;

public class EmptyDBFixture {

    public static void setUp(Connection con) {
        try {

            Statement st = con.createStatement();

            // start transaction
            con.setAutoCommit(false);

            // delete info in table
            st.addBatch("delete from instructor_booking");
            st.addBatch("delete from instructor_sports");
            st.addBatch("delete from instructor");
            st.addBatch("delete from queue_facility");
            st.addBatch("delete from facility_booking");
            st.addBatch("delete from Hotel_user");
            st.addBatch("delete from unpaid_reservations");
            st.addBatch("delete from Reservation_Customer");
            st.addBatch("delete from Reservation");
            st.addBatch("delete from Room");
            st.addBatch("delete from Customer");
            st.addBatch("delete from Facility");
            st.addBatch("delete from Reservation");
            st.addBatch("delete from Room");
            st.addBatch("delete from Customer");
            // reseting sequence
            st.addBatch("drop sequence reservationseq");
            st.addBatch("create sequence reservationseq start with 1");
            st.addBatch("drop sequence customerseq");
            st.addBatch("create sequence customerseq start with 1");
            st.addBatch("drop sequence facility_bookingseq");
            st.addBatch("create sequence facility_bookingseq start with 1");
            st.addBatch("drop sequence hotel_userseq");
            st.addBatch("create sequence hotel_userseq start with 1");
            st.addBatch("drop sequence queue_facilityseq");
            st.addBatch("create sequence queue_facilityseq start with 1");
            st.addBatch("drop sequence instructorSeq");
            st.addBatch("create sequence instructorSeq start with 1");
            st.addBatch("drop sequence instructor_bookingSeq");
            st.addBatch("create sequence instructor_bookingSeq start with 1");
            
            st.executeBatch();
            // end transaction
            con.commit();
        } catch (SQLException e) {
            try {
                con.rollback();
            } catch (SQLException e1) {
                e1.printStackTrace();
            }
            System.out.println("Fail in EmptyDBFixture.setup()");
            System.out.println(e.getMessage());
        }
    }
}
