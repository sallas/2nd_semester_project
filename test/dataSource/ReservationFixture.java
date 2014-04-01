package dataSource;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;

public class ReservationFixture {

    public static void setUp(Connection con) {
        try {

            Statement st = con.createStatement();

            // start transaction
            con.setAutoCommit(false);

            // delete info in table
            st.addBatch("delete from queue_facility");
            st.addBatch("delete from facility_booking");
            st.addBatch("delete from Hotel_user");
            st.addBatch("delete from Reservation");
            st.addBatch("delete from Room");
            st.addBatch("delete from Customer");
            st.addBatch("delete from Facility");
            // reseting sequence
            st.addBatch("drop sequence reservationseq");
            st.addBatch("create sequence reservationseq start with 4");
            st.addBatch("drop sequence customerseq");
            st.addBatch("create sequence customerseq start with 3");
            st.addBatch("drop sequence facility_bookingseq");
            st.addBatch("create sequence facility_bookingseq start with 3");
            st.addBatch("drop sequence hotel_userseq");
            st.addBatch("create sequence hotel_userseq start with 3");
            st.addBatch("drop sequence queue_facilityseq");
            st.addBatch("create sequence queue_facilityseq start with 2");
            // insert data into rooms
            String insert = "insert into room values ";
            st.addBatch(insert + "(100,'double')");
            st.addBatch(insert + "(101,'single')");
            // insert data into customer
            insert = "insert into customer values ";
            st.addBatch(insert + "(1,'address1','country1','firstName1','lastName1'"
                    + ",'33','email1','agency1')");
            st.addBatch(insert + "(2,'address2','country2','firstName2'"
                    + ",'lastName2','332','email2','agency2')");
            // insert data into reservation
            insert = "insert into reservation values ";
            st.addBatch(insert + "(1, 100, 1,to_date('24-03-2014', 'DD-MM-YYYY'),"
                    + "to_date('24-04-2014', 'DD-MM-YYYY'))");
            st.addBatch(insert + "(2, 101, 2,to_date('23-01-2014', 'DD-MM-YYYY'),"
                    + "to_date('27-01-2014', 'DD-MM-YYYY'))");
            st.addBatch(insert + "(3, 101, 1,to_date('01-02-2014', 'DD-MM-YYYY'),"
                    + "to_date('06-02-2014', 'DD-MM-YYYY'))");
            //insert into facility
            insert = "insert into facility values ";
            st.addBatch(insert + "(1, 'Golden arena', 'volleyball', 20, 1, 1, 0)");
            st.addBatch(insert + "(2, 'Big tennis', 'tennis', 4, 1, 0, 1)");
            //insert into hotel_user
            insert = "insert into hotel_user values ";
            st.addBatch(insert + "(1, 'user1', 'password1', 'guest', 1, 0)");
            st.addBatch(insert + "(2, 'user2', 'password2', 'guest', 2, 0)");
            //insert into facility_booking
            insert = "insert into facility_booking values ";
            st.addBatch(insert + "(1, 1, to_date('24-03-2014', 'DD-MM-YYYY'),"
                    + "2, 1)");
            st.addBatch(insert + "(2, 2, to_date('24-03-2014', 'DD-MM-YYYY'),"
                    + "4, 1)");
            insert = "insert into queue_facility values ";
            st.addBatch(insert + "(1, 2, 1)");
            st.executeBatch();
            // end transaction
            con.commit();
        } catch (SQLException e) {
            try {
                con.rollback();
            } catch (SQLException e1) {
                e1.printStackTrace();
            }
            System.out.println("Fail in ReservationFixture.setup()");
            System.out.println(e.getMessage());
        }
    }
}
