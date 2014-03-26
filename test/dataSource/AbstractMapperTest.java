package dataSource;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;

public class AbstractMapperTest {
    
    Connection con;
    AbstractMapperDummy am;
    TestDBConnector connector = new TestDBConnector();
    
    public AbstractMapperTest() {
    }
    
    @Before
    public void init() {
        con = connector.getConnection();
        ReservationFixture.setUp(con);
        am = new AbstractMapperDummy(con);
    }
    
    @After
    public void tearDown() {
        connector.releaseConnection(con);
    }
    
    @Test
    public void testExecuteSQLQuerySimpleSelectStatement() throws SQLException {
        ResultSet rs = am.executeSQLQueryPrivate("SELECT type FROM room WHERE ID= ?",
                "Problem with my test statement", 101);
        assertTrue(rs.next());
        rs.close();
    }
    
    @Test
    public void testExecuteSQLQueryWrongSelectStatement() throws SQLException {
        ResultSet rs = am.executeSQLQueryPrivate("SELECT t!pe FROM room WHERE ID= ?",
                "Problem with my test statement", 101);
        assertNull(rs);
    }
    
    @Test
    public void testExecuteSQLQueryComplexSelectStatement() throws SQLException {
        ResultSet rs = am.executeSQLQueryPrivate(
                "SELECT first_name FROM customer WHERE last_name = ? AND phone = ?",
                "Problem with my test statement", "lastName1", "33");
        assertTrue(rs.next());
    }

}