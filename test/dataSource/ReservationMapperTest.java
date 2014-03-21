package dataSource;

import domain.Reservation;
import java.sql.Connection;
import java.sql.Date;
import java.sql.DriverManager;
import java.sql.SQLException;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;

public class ReservationMapperTest {

    Connection con;
    private String id = "SEM2_TEST_GR24";
    private String pw = "SEM2_TEST_GR24";
    ReservationMapper rm;

    public ReservationMapperTest() {
    }

    /*
    * Sets up reservation mapper with our connection
    */
    @Before
    public void init() {
        getConnection();
        ReservationMapperFixture.setUp(con);
        rm = new ReservationMapper(con);
    }

    @After
    public void tearDown() throws Exception {
        releaseConnection();
    }

    /*
    * Checks so the correct reservation is returned by the mapper
    */
    @Test
    public void testGetReservationMatch() {
        Reservation r = rm.getReservation(1);
        assertTrue("GetOrderMatch failed1", r != null);
        assertTrue("GetOrderMatch failed2", r.getID() == 1);
    }
    
    /*
    * Checks so no reservation is returned when bad ID is given
    */
    @Test
    public void testGetReservationNoMatch() {
        Reservation r = rm.getReservation(33);
        assertTrue("GetOrderMatch failed1", r == null);
    }
    
    /*
    * Checks so the reservation is inserted 
    */
    @Test
    public void testSaveReservationNewID()
    {
        Reservation r = new Reservation(1, 1, 1, new Date(01,01,2014), 4);
        boolean status = rm.saveReservation(r);
        assertTrue(status);
    }
    
    /*
    * Checks so the reservation is not inserted when the Customer ID
    * doesn't exist in database.
    */
    @Test
    public void testSaveReservationNoMatchCustomerID()
    {
        Reservation r = new Reservation(1, 1, 99, new Date(01,01,2014), 4);
        boolean status = rm.saveReservation(r);
        assertFalse(status);
    }
    
    /*
    * Checks so the reservation is not inserted when the Room ID
    * doesn't exist in database.
    */
    @Test
    public void testSaveReservationNoMatchRoomID()
    {
        Reservation r = new Reservation(1, 99, 1, new Date(01,01,2014), 4);
        boolean status = rm.saveReservation(r);
        assertFalse(status);
    }
    
    private void getConnection() {
        try {
            con = DriverManager.getConnection("jdbc:oracle:thin:@datdb.cphbusiness.dk:1521:dat", id, pw);
        }
        catch (SQLException e) {
            System.out.println("fail in getConnection() - Did you add your Username and Password");
            System.out.println(e);
        }
    }

    public void releaseConnection() {
        try {
            con.close();
        }
        catch (Exception e) {
            System.err.println(e);
        }
    }
}
