package dataSource;

import domain.FacilityBooking;
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
    QueueMapperInterface qm;
    TestDBConnector connector = new TestDBConnector();

    public FacilityBookingTest() {
    }

    @Before
    public void init() {
        con = connector.getConnection(); 
        ReservationFixture.setUp(con);
        fbm = new FacilityBookingMapper(con);
        qm = new QueueMapper(con);
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

    /*
     * Tests so the mapper returns true if date+timeslot is not booked
     */
    @Test
    public void testCheckAvailableFacilityBookingAvailableBooking() {
        FacilityBooking fb = new FacilityBooking(1, 1, Date.valueOf("2099-01-01"), 
                1, 999, true);
        boolean status = fbm.checkAvailableFacilityBooking(fb);
        assertTrue(status);
    }

    /*
     * Tests so the mapper returns false if date+timeslot is already booked
     */
    @Test
    public void testCheckAvailableFacilityBookingNotAvailableBooking() {
        FacilityBooking fb = new FacilityBooking(1, 1, Date.valueOf("2014-03-24"), 
                2, 999, true);
        boolean status = fbm.checkAvailableFacilityBooking(fb);
        assertFalse(status);
    }

    /*
     * Tests so the correct amount of bookings are returned
     * when the date has bookins
     */
    @Test
    public void testGetAllBookingsOfSpecificDateHasBooking() {
        List<FacilityBooking> r = fbm.getAllBookingsOfSpecificDate(Date.valueOf("2014-03-24"));
        assertTrue(r.size() == 2);
    }

    /*
     * Tests so that no bookings are returned when the date has no bookings
     */
    @Test
    public void testGetAllBookingsOfSpecificDateNoBooking() {
        List<FacilityBooking> r = fbm.getAllBookingsOfSpecificDate(Date.valueOf("2099-02-31"));
        assertTrue(r.isEmpty());
    }

    /*
     * Tests so the correct amount of bookings are returned
     * when the date+timeslot has bookings
     */
    @Test
    public void testGetAllBookingsOfSpecificDateAndUserMatch() {
        List<FacilityBooking> r
                = fbm.getAllBookingsOfSpecificDateAndUser(
                        Date.valueOf("2014-03-24"), 1);
        assertTrue(r.size() == 2);
    }

    /*
     * Tests so no bookings are returned 
     * when the date+timeslot has no bookings
     */
    @Test
    public void testGetAllBookingsOfSpecificDateAndUserNoMatch() {
        List<FacilityBooking> r
                = fbm.getAllBookingsOfSpecificDateAndUser(
                        Date.valueOf("2099-03-24"), 1);
        assertTrue(r.isEmpty());
    }

    /*
     * Tests so the correct amount of bookings are returned
     * when the user has a booking of date+timeslot
     */
    @Test
    public void testGetAllBookingsOfSpecificDateTimeslotUserMatch() {
        List<FacilityBooking> r
                = fbm.getAllBookingsOfSpecificDateTimeslotUser(
                        Date.valueOf("2014-03-24"), 1, 2);
        assertTrue(r.size() == 1);
    }

    /*
     * Tests so no bookings are returned 
     * when the user id doesn't exist
     */
    @Test
    public void testGetAllBookingsOfSpecificDateTimeslotUserNoMatchUser() {
        List<FacilityBooking> r
                = fbm.getAllBookingsOfSpecificDateTimeslotUser(
                        Date.valueOf("2014-03-24"), 1, 9);
        assertTrue(r.isEmpty());
    }

    /*
     * Tests so when a proper facility booking is inserted true is returned
     */
    @Test
    public void testsaveFacilityBookingNoProblem() {
        FacilityBooking fb = new FacilityBooking(4, 1, Date.valueOf("2099-03-24"), 
                3, 1, true);
        boolean status = fbm.saveFacilityBooking(fb);
        assertTrue(status);
    }

    /*
     * Tests so when making a booking and a none existing facilityID is used
     * false is returend
     */
    @Test
    public void testsaveFacilityBookingNoMatchFacilityID() {
        FacilityBooking fb = new FacilityBooking(3, -1, Date.valueOf("2099-03-24"), 
                3, 1, true);
        boolean status = fbm.saveFacilityBooking(fb);
        assertFalse(status);
    }

    /*
     * Tests so when making a booking and a none existing userID is used
     * false is returend
     */
    @Test
    public void testsaveFacilityBookingNoMatchUser() {
        FacilityBooking fb = new FacilityBooking(3, 1, Date.valueOf("2099-03-24"), 
                3, -1, true);
        boolean status = fbm.saveFacilityBooking(fb);
        assertFalse(status);
    }
    
    //Tests if the remove method works when there is a match
    @Test
    public void testRemoveFacilityBookingMatch(){
        assertTrue(fbm.removeFacilityBooking(3));
    }
    
    @Test
    public void testRemoveFacilityBookingNoMatch(){
        assertFalse(fbm.removeFacilityBooking(999999));
    }
    
    @Test
    public void testDeleteFacilityBookingByReservationID(){
        qm.deleteQueueEntryByReservationID(2);
        assertTrue(fbm.deleteFacilityBookingByReservationID(2));
    }
}
