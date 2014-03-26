package dataSource;

import java.sql.Connection;
import java.sql.ResultSet;

public class AbstractMapperDummy extends AbstractMapper{

    public AbstractMapperDummy(Connection con) {
        super(con);
    }

    /*
     * A dummy map for the private method.
     */
    public ResultSet executeSQLQueryPrivate(String statement, String exMessage, Object... values) {
        return executeSQLQuery(statement, exMessage, values);
    }
}
