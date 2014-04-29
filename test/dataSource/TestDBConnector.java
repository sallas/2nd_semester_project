package dataSource;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class TestDBConnector {

    private final String id = "cphbk77";
    private final String pw = "cphbk77";

    public Connection getConnection() {
        Connection con = null;
        try {
            con = DriverManager.getConnection(
                    "jdbc:oracle:thin:@datdb.cphbusiness.dk:1521:dat", id, pw);
        } catch (SQLException e) {
            System.out.println(
                    "fail in getConnection() - "
                    + "Did you add your Username and Password");
            System.out.println(e);
        }
        return con;
    }

    public void releaseConnection(Connection con) {
        try {
            con.close();
        } catch (SQLException e) {
            System.err.println(e);
        }
    }
}
