package dataSource;

import domain.Customer;
import java.sql.Connection;
import java.sql.ResultSet;
import java.util.ArrayList;

public class AbstractMapperDummy extends AbstractMapper{

    public AbstractMapperDummy(Connection con) {
        super(con, "customer", Customer.class);
    }

    /*
     * Dummy maps for private methods.
     */
    public ResultSet executeSQLQueryPrivate(String statement, String exMessage, Object... values) {
        return executeSQLQuery(statement, exMessage, values);
    }
    
    public <T> ArrayList<T> executeQueryAndGatherResultsPrivate(
            Class<T> objectType,
            String statement,
            String exMessage,
            Object... values) throws InstantiationException, IllegalAccessException, NoSuchFieldException {
        return executeQueryAndGatherResults(objectType, statement, exMessage, values);
    }
    
    public int executeSQLInsertPrivate(String statement, String exMessage, Object... values) {
        return executeSQLInsert(statement, exMessage, values);
    }
}
