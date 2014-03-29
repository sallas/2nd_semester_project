package dataSource;

import domain.FacilityBooking;
import domain.Reservation;
import java.sql.Connection;
import java.sql.Date;
import java.util.List;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;

public class FacilityBookingTest {

    Connection con;
    FacilityBookingMapper fbm;
    TestDBConnector connector = new TestDBConnector();

    public FacilityBookingTest() {
    }

    @Before
    public void init() {
        con = connector.getConnection();
        ReservationFixture.setUp(con);
        fbm = new FacilityBookingMapper(con);
    }

    @After
    public void tearDown() {
        connector.releaseConnection(con);
    }

    /*
     * Checks all bookings.
     */
    @Test
    public void testGetAllBookings() {
        List<FacilityBooking> r = fbm.getAllBookings();
        assertTrue(r != null);
        assertTrue(r.get(0).getID() == 1);
    }

    @Test
    public void testCheckAvailableFacilityBookingAvailableBooking() {
        FacilityBooking fb = new FacilityBooking(1, 1, Date.valueOf("2099-01-01"), 1, 999);
        boolean status = fbm.checkAvailableFacilityBooking(fb);
        assertTrue(status);
    }

    @Test
    public void testCheckAvailableFacilityBookingNotAvailableBooking() {
        FacilityBooking fb = new FacilityBooking(1, 1, Date.valueOf("2014-03-24"), 2, 999);
        boolean status = fbm.checkAvailableFacilityBooking(fb);
        assertFalse(status);
    }

    @Test
    public void testGetAllBookingsOfSpecificDateHasBooking() {
        List<FacilityBooking> r = fbm.getAllBookingsOfSpecificDate(Date.valueOf("2014-03-24"));
        assertTrue(r.size() == 2);
        assertEquals(r.get(0).getID(), 1);
    }

    @Test
    public void testGetAllBookingsOfSpecificDateNoBooking() {
        List<FacilityBooking> r = fbm.getAllBookingsOfSpecificDate(Date.valueOf("2099-02-31"));
        assertTrue(r.isEmpty());
    }

    @Test
    public void testGetAllBookingsOfSpecificDateAndUserMatch() {
        List<FacilityBooking> r
                = fbm.getAllBookingsOfSpecificDateAndUser(
                        Date.valueOf("2014-03-24"), 1);
        assertTrue(r.size() == 2);
    }
    
    @Test
    public void testGetAllBookingsOfSpecificDateTimeslotUserMatch() {
        List<FacilityBooking> r
                = fbm.getAllBookingsOfSpecificDateTimeslotUser(
                        Date.valueOf("2014-03-24"), 1, 2);
        assertTrue(r.size() == 1);
    }
    
    

//    @Test
//    public void testsaveFacilityBookingNoProblem() {
//        FacilityBooking fb = new FacilityBooking(3, 1, Date.valueOf("2099-03-24"), 3, 1);
//        boolean status = fbm.saveFacilityBooking(fb);
//        assertTrue(status);
//    }
//    
//    @Test
//    public void testsaveFacilityBookingConflictingBooking() {
//        FacilityBooking fb = new FacilityBooking(3, 1, Date.valueOf("2014-03-24"), 2, 1);
//        boolean status = fbm.saveFacilityBooking(fb);
//        assertFalse(status);
//    }
}
