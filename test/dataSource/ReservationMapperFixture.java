package dataSource;

import java.sql.Connection;
import java.sql.Date;
import java.sql.SQLException;
import java.sql.Statement;

public class ReservationMapperFixture {

    public static void setUp(Connection con) {
        try {

            Statement st = con.createStatement();

            // start transaction
            con.setAutoCommit(false);

            // delete info in table
            st.addBatch("delete from Reservation");
            st.addBatch("delete from Room");
            st.addBatch("delete from Customer");
            // reseting sequence
            st.addBatch("drop sequence reservationseq");
            st.addBatch("create sequence reservationseq start with 3");
            // insert data into rooms
            String insert = "insert into room values ";
            st.addBatch(insert + "(1,'double')");
            st.addBatch(insert + "(2,'single')");
            // insert data into customer
            insert = "insert into customer values ";
            st.addBatch(insert + "(1,'home','sweden','tobias','karlsson'"
                    + ",33,'email','agency')");
            st.addBatch(insert + "(2,'home2','sweden2','tobias2'"
                    + ",'karlsfather2',332,'email2','agency2')");
            // insert data into reservation
            insert = "insert into reservation values ";
            st.addBatch(insert + "(1, 1, 1,'01-JAN-2014',4)");
            int[] opcounts = st.executeBatch();

            // end transaction
            con.commit();
        }
        catch (Exception e) {
            try {
                con.rollback();
            }
            catch (SQLException e1) {
                e1.printStackTrace();
            }
            System.out.println("Fail in RoomFixture.setup()");
            System.out.println(e.getMessage());
        }
    }
}
