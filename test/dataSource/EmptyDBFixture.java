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
            st.addBatch("delete from Reservation");
            st.addBatch("delete from Room");
            st.addBatch("delete from Customer");
            // reseting sequence
            st.addBatch("drop sequence reservationseq");
            st.addBatch("create sequence reservationseq start with 4");
            st.addBatch("drop sequence customerseq");
            st.addBatch("create sequence customerseq start with 3");
            
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