package dataSource;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.lang.reflect.Field;

public abstract class AbstractMapper {
    protected final Connection con;

    public AbstractMapper(Connection con) {
        this.con = con;
    }
    
    /*
     * Utility function for executing SQL Queries with automatic mapping
     * and exception message. 
     * Automates sql value mapping and execution.
     * The result set is not closed so close after usage.
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
    
    /*
     * Execute the query, gather results in array list.
     * Automates mapping of query set and mapping of result set.
     * objectType - Type of object to be returned.
     * statement - SQL statement.
     * exMessage - exception message.
     * resultNames - array of names to the properties. Must be same len as resultArray.
     * resultArray - array with result types to be mapped. 
     * [0, 0, 1] ≡ [Integer, Integer, String] ;2 - Date 3 - Boolean
     * values - values for the SQL statement
     */
    protected <T> ArrayList<T> executeQueryAndGatherResults(
            Class<T> objectType,
            String statement,
            String exMessage, 
            String[] resultNames,
            int[] resultArray,
            Object... values) {
        ArrayList<T> result = new ArrayList();
        int i;
        ResultSet rs = executeSQLQuery(statement,
                exMessage, values);
        try {
            while (rs.next()) {
                //New T object instance
                T object = objectType.newInstance();
                for(i=0; i<resultArray.length; i++){
                    //Set fields from result names with reflection.
                    Field field = objectType.getDeclaredField(resultNames[i]);
                    field.setAccessible(true);
                    switch(resultArray[i]){
                        case 0:
                            field.set(object, rs.getInt(i+1));
                            break;
                        case 1:
                            field.set(object, rs.getString(i+1));
                            break;
                        case 2:
                            field.set(object, rs.getDate(i+1));
                            break;
                        case 3:
                            field.set(object, rs.getBoolean(i+1));
                            break;
                    }
                    field.setAccessible(false);
                }
                //add T object to the final result set.
                result.add(object);
            }
            rs.close();
        } catch (SQLException ex) {
            System.out.println(exMessage);
            System.out.println(ex.getMessage());
        } catch (InstantiationException ex) {
            System.out.println(exMessage);
            System.out.println(ex.getMessage());
        } catch (IllegalAccessException ex) {
            System.out.println(exMessage);
            System.out.println(ex.getMessage());
        } catch (NoSuchFieldException ex) {
            System.out.println(exMessage);
            System.out.println(ex.getMessage());
        } catch (SecurityException ex) {
            System.out.println(exMessage);
            System.out.println(ex.getMessage());
        }
        return result;
    }
    
    /*
     * Utility function for executing SQL Inserts with automatic mapping
     * and exception message.
     * Returns how many rows were inserted in db.
     */
    protected int executeSQLInsert(String statement, String exMessage, Object... values) {
        int result = 0;
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
            result = st.executeUpdate();
        } catch (SQLException ex) {
            System.out.println(exMessage);
            System.out.println(ex.getMessage());
        } finally {
            try {
                if (st != null) {
                    st.close();
                }
            } catch (SQLException e) {
                System.out.println(exMessage);
                System.out.println(e.getMessage());
            }
        }
        return result;
    }
}
