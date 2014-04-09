package dataSource;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.lang.reflect.Field;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

public abstract class AbstractMapper {

    protected final Connection con;
    protected final String tableName;
    protected final Class classType;

    public AbstractMapper(Connection con, String tableName, Class classType) {
        this.con = con;
        this.tableName = tableName;
        this.classType = classType;
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
            System.out.println(exMessage + "SQLException execute query");
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
            Object... values) {
        ArrayList<T> result = new ArrayList();
        int i;
        ResultSet rs = executeSQLQuery(statement,
                exMessage, values);
        Field[] fs = objectType.getDeclaredFields();
        try {
            while (rs.next()) {
                //New T object instance
                T object = objectType.newInstance();
                for (i = 0; i < fs.length; i++) {
                    //Set fields from result names with reflection.
                    Field field = objectType.getDeclaredField(fs[i].getName());
                    field.setAccessible(true);
                    String s = fs[i].getGenericType().toString();
                    if (s.equalsIgnoreCase("int")) {
                        field.set(object, rs.getInt(i + 1));
                    } else if (s.equalsIgnoreCase("class java.lang.String")) {
                        field.set(object, rs.getString(i + 1));
                    } else if (s.equalsIgnoreCase("class java.sql.Date")) {
                        field.set(object, rs.getDate(i + 1));
                    } else if (s.equalsIgnoreCase("boolean")) {
                        field.set(object, rs.getBoolean(i + 1));
                    } else {
                        throw new NoSuchFieldException();
                    }

                    field.setAccessible(false);
                }
                //add T object to the final result set.
                result.add(object);
            }
            rs.close();
        } catch (SQLException ex) {
            System.out.println(exMessage + " SQLException");
            System.out.println(ex.getMessage());
        } catch (InstantiationException ex) {
            System.out.println(exMessage + " InstantiationException");
            System.out.println(ex.getMessage());
        } catch (IllegalAccessException ex) {
            System.out.println(exMessage + " IllegalAccessException");
            System.out.println(ex.getMessage());
        } catch (NoSuchFieldException ex) {
            System.out.println(exMessage + " NoSuchFieldException");
            System.out.println(ex.getMessage());
        } catch (SecurityException ex) {
            System.out.println(exMessage + " SecurityException");
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
            System.out.println(ex.getErrorCode());

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

    protected <T> List<T> generalSearch(Class<T> objectType,
            String tableName, String columnName,
            String exMessage, Object variable) {
        return executeQueryAndGatherResults(
                objectType,
                "SELECT * FROM " + tableName
                + " WHERE " + columnName + " = ?",
                exMessage, variable);
    }

    protected <T> List<T> generalSearch(String columnName,
            String exMessage, Object variable) {
        return executeQueryAndGatherResults(
                classType,
                "SELECT * FROM " + tableName
                + " WHERE " + columnName + " = ?",
                exMessage, variable);
    }
    
    protected boolean generalDelete(String columnName, String exMessage,
            Object variable) {
        return executeSQLInsert("DELETE FROM " + tableName + " WHERE " 
                + columnName + " = ?", exMessage, variable)
                == 1;
    }

    protected int getSequenceNumber(String statement, String exMessage) {
        int seq = 0;
        ResultSet rs = executeSQLQuery(statement,
                exMessage);
        try {
            if (rs.next()) {
                seq = rs.getInt(1);
            }
        } catch (SQLException ex) {
            Logger.getLogger(AbstractMapper.class.getName()).log(Level.SEVERE, null, ex);
        }
        return seq;
    }

}
