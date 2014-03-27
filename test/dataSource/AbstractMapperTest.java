package dataSource;

import domain.Customer;
import domain.Room;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
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
    
    @Test
    public void testExecuteQueryAndGatherResultsSimpleRoom() 
            throws InstantiationException, NoSuchFieldException, IllegalAccessException {
        
        ArrayList<Room> ar = am.executeQueryAndGatherResultsPrivate(Room.class,
                "SELECT * FROM room", 
                "Problem with test statement 2",
                 new String[]{"ID", "type"}, new int[]{0, 1});
        assertEquals(ar.get(0).getID(), 100);
        assertEquals(ar.get(1).getType(), "single");
    }
    
    @Test
    public void testExecuteQueryAndGatherResultsComplex() 
            throws InstantiationException, IllegalAccessException, NoSuchFieldException {
        ArrayList<Customer> ar = am.executeQueryAndGatherResultsPrivate(Customer.class,
                "SELECT * FROM customer WHERE ID = ? AND country = ?", 
                "Problem with test statement 2",
                 new String[]{"ID", "addres", "country", "first_name", "last_name", "phone", "email", "travel_agency"},
                 new int[]{0, 1, 1, 1, 1, 1, 1, 1},
                 1, "country1");
        assertEquals(ar.get(0).getID(), 1);
        assertEquals(ar.size(), 1);
    }

    @Test
    public void testExecuteQueryAndGatherResultsNoResults() 
            throws InstantiationException, IllegalAccessException, NoSuchFieldException {
        ArrayList<Customer> ar = am.executeQueryAndGatherResultsPrivate(Customer.class,
                "SELECT * FROM customer WHERE ID = ? AND country = ?", 
                "Problem with test statement 2",
                 new String[]{"ID", "addres", "country", "first_name", "last_name", "phone", "email", "travel_agency"},
                 new int[]{0, 1, 1, 1, 1, 1, 1, 1},
                 101, "country1");
        assertEquals(ar.size(), 0);
    }
}