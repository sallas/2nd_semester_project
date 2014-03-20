package dataSource;

import java.sql.*;

public class RoomMapperFixture {

    public static void setUp(Connection con) {
        try {

            Statement st = con.createStatement();

            // start transaction
            con.setAutoCommit(false);

            // create table
            st.addBatch("delete from Room");

            st.addBatch("drop sequence orderseq");
            st.addBatch("create sequence orderseq start with 3");
            // insert tuples
            String insert = "insert into room values ";
            st.addBatch(insert + "(1,'double')");
            st.addBatch(insert + "(2,'single')");
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
