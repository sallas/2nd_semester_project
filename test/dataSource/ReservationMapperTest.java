package dataSource;

import domain.Reservation;
import java.sql.Connection;
import java.sql.Date;
import java.util.List;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;

public class ReservationMapperTest {

    Connection con;
    ReservationMapper rm;
    TestDBConnector connector = new TestDBConnector();

    public ReservationMapperTest() {
    }

    /*
     * Sets up reservation mapper with our connection
     */
    @Before
    public void init() {
        con = connector.getConnection();
        ReservationFixture.setUp(con);
        rm = new ReservationMapper(con);
    }

    @After
    public void tearDown() throws Exception {
        connector.releaseConnection(con);
    }

    /*
     * Checks so the correct reservation is returned by the mapper
     */
    @Test
    public void testGetReservationMatch() {
        Reservation r = rm.getReservation(1);
        assertTrue(r != null);
        assertTrue(r.getID() == 1);
    }

    /*
     * Checks so no reservation is returned when bad ID is given
     */
    @Test
    public void testGetReservationNoMatch() {
        Reservation r = rm.getReservation(33);
        assertTrue(r == null);
    }
    
     /*
     * Checks so no reservation is returned when bad ID is given
     */
    @Test
    public void testGetReservationNoReservationsInDB() {
        EmptyDBFixture.setUp(con);
        Reservation r = rm.getReservation(33);
        assertTrue(r == null);
    }

    /*
     * Checks so the reservation is inserted 
     */
    @Test
    public void testSaveReservationNewID() {
        Reservation r = new Reservation(1, 100, 1, new Date(01, 03, 2014), 4);
        boolean status = rm.saveReservation(r);
        assertTrue(status);
    }

    /*
     * Checks so the reservation is not inserted when the Customer ID
     * doesn't exist in database.
     */
    @Test
    public void testSaveReservationNoMatchCustomerID() {
        Reservation r = new Reservation(1, 100, 99, new Date(01, 01, 2014), 4);
        boolean status = rm.saveReservation(r);
        assertFalse(status);
    }
    
    /*
     * Checks so the reservation is not inserted when the DB 
     * doesn't have any Rooms or Customers in it
     */
    @Test
    public void testSaveReservationNoRoomsAndCustomersInDB() {
        EmptyDBFixture.setUp(con);
        Reservation r = new Reservation(1, 100, 99, new Date(01, 01, 2014), 4);
        boolean status = rm.saveReservation(r);
        assertFalse(status);
    }

    /*
     * Checks so the reservation is not inserted when the Room ID
     * doesn't exist in database.
     */
    @Test
    public void testSaveReservationNoMatchRoomID() {
        Reservation r = new Reservation(1, 99, 1, new Date(01, 01, 2014), 4);
        boolean status = rm.saveReservation(r);
        assertFalse(status);
    }

    /*
     * Checks so the correct amount of reservations are returned 
     * when looking for single rooms
     */
    @Test
    public void testgetAllReservationsOfSpecificTypeSingle() {
        List<Reservation> resList = rm.getAllReservationsOfSpecificType("single");
        assertTrue(resList.size() == 2);
    }
    
    /*
     * Checks so the correct amount of reservations are returned 
     * when looking for single rooms
     */
    @Test
    public void testgetAllReservationsOfSpecificTypeNoReservationsInDB() {
        EmptyDBFixture.setUp(con);
        List<Reservation> resList = rm.getAllReservationsOfSpecificType("single");
        assertTrue(resList.isEmpty());
    }
    

    /*
     * Checks so the correct amount of reservations are returned 
     * when looking for double rooms
     */
    @Test
    public void testgetAllReservationsOfSpecificTypeDouble() {
        List<Reservation> resList = rm.getAllReservationsOfSpecificType("double");
        assertTrue(resList.size() == 1);
    }

}
