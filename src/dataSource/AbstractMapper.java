package dataSource;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public abstract class AbstractMapper {
    protected final Connection con;

    public AbstractMapper(Connection con) {
        this.con = con;
    }
    
    /*
     * Utility function for executing SQL Queries with automatic mapping
     * and exception message. The result set is not closed so close after usage.
     */
    protected ResultSet executeSQLQuery(String statement, String exMessage, Object... values) {
        ResultSet result = null;
        int counter = 1;
        PreparedStatement st = null;
        try {
            st = con.prepareStatement(statement);
            for (Object value : values) {
                if (value instanceof Integer) {
                    st.setInt(counter, (int) value);
                } else if (value instanceof String) {
                    st.setString(counter, (String) value);
                } else if (value instanceof Date) {
                    st.setDate(counter, (Date) value);
                }
                counter++;
            }
            result = st.executeQuery();
        } catch (SQLException ex) {
            System.out.println(exMessage);
            System.out.println(ex.getMessage());
        }
        return result;
    }
    
}
