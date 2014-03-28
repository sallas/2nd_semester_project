package dataSource;

import java.sql.Connection;
import java.sql.ResultSet;
import java.util.ArrayList;

public class AbstractMapperDummy extends AbstractMapper{

    public AbstractMapperDummy(Connection con) {
        super(con);
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
            String[] resultNames,
            int[] resultArray,
            Object... values) throws InstantiationException, IllegalAccessException, NoSuchFieldException {
        return executeQueryAndGatherResults(objectType, statement, exMessage, resultNames, resultArray, values);
    }
    
    public int executeSQLInsertPrivate(String statement, String exMessage, Object... values) {
        return executeSQLInsert(statement, exMessage, values);
    }
}
