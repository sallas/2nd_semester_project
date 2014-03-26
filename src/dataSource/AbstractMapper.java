package dataSource;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

public abstract class AbstractMapper {
    protected final Connection con;

    public AbstractMapper(Connection con) {
        this.con = con;
    }
    
    /*
     * Utility function for executing SQL Queries with automatic mapping
     * and exception message.
     */
    private ResultSet executeSQLQuery(String statement, String exMessage, Object... values) {
        ResultSet result = null;
        int counter = 1;
        PreparedStatement st = null;
        try {
            st = con.prepareStatement(statement);
            for (Object value : values) {
                if (value instanceof Integer){
                    st.setInt(counter, (int) value);
                } else if (value instanceof String) {
                    st.setString(counter, (String) value);
                } else if (value instanceof Date) {
                    st.setDate(counter, (Date) value);
                }
                counter++;
            }
            st.executeQuery();
        } catch (SQLException ex) {
            System.out.println(exMessage);
            System.out.println(ex.getMessage());
        } finally {
            try {
                st.close();
            } catch (SQLException e) {
                System.out.println(exMessage);
                System.out.println(e.getMessage());
            }
        }
        return result;
    }
    
}
