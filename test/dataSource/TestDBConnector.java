package dataSource;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class TestDBConnector {

    private String id = "SEM2_TEST_GR24";
    private String pw = "SEM2_TEST_GR24";

    public Connection getConnection() {
        Connection con = null;
        try {
            con = DriverManager.getConnection("jdbc:oracle:thin:@datdb.cphbusiness.dk:1521:dat", id, pw);
        }
        catch (SQLException e) {
            System.out.println("fail in getConnection() - Did you add your Username and Password");
            System.out.println(e);
        }
        return con;
    }

    public void releaseConnection(Connection con) {
        try {
            con.close();
        }
        catch (Exception e) {
            System.err.println(e);
        }
    }
}
