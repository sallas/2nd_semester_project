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

    @Before
    public void setUp() {
        getConnection();
        ReservationMapperFixture.setUp(con);
        rm = new ReservationMapper(con);
    }

    @After
    public void tearDown() throws Exception {
        releaseConnection();
    }

    @Test
    public void testGetReservationMatch() {
        Reservation r = rm.getReservation(1);
        assertTrue("GetOrderMatch failed1", r != null);
        assertTrue("GetOrderMatch failed2", r.getID() == 1);
    }
    
    @Test
    public void testGetReservationNoMatch() {
        Reservation r = rm.getReservation(33);
        assertTrue("GetOrderMatch failed1", r == null);
    }
    
    @Test
    public void testSaveReservationNewID()
    {
        Reservation r = new Reservation(1, 1, 1, new Date(01,01,2014), 4);
        boolean status = rm.saveReservation(r);
        assertTrue(status);
    }
    
    @Test
    public void testSaveReservationNoMatchCustomerID()
    {
        Reservation r = new Reservation(1, 1, 99, new Date(01,01,2014), 4);
        boolean status = rm.saveReservation(r);
        assertFalse(status);
    }
    
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
