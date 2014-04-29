package domain;

import dataSource.EmptyDBFixture;
import dataSource.ReservationFixture;
import dataSource.TestDBConnector;
import java.sql.Connection;
import java.sql.Date;
import java.sql.SQLException;
import java.util.List;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.junit.Test;
import static org.junit.Assert.*;
import org.junit.After;
import org.junit.Before;

public class ControllerTest {

    Connection con;
    private Controller controller;
    TestDBConnector connector = new TestDBConnector();

    public ControllerTest() {
    }

    @Before
    public void setUp() {
        con = connector.getConnection();
        controller = new Controller(con);
        ReservationFixture.setUp(con);
    }

    @After
    public void tearDown() {
        if (con != null) {
            try {
                con.close();
            } catch (SQLException ex) {
                Logger.getLogger(ControllerTest.class.getName()).log(Level.SEVERE,
                        null, ex);
            }
        }
    }


    /*
     *   Checks so -1 is returned if no room of speciefied type is available 
     *    on the specified dates
     */
    @Test
    public void testGetAvailableRoomOfSpecificTypeNoRoomAvailable() {
        Date arrivalDate = Date.valueOf("2014-03-25");
        Date departureDate = Date.valueOf("2014-03-29");
        List<Integer> IDs = controller.getAvailableRoomIDsOfTypeBetweenDates("double", arrivalDate, departureDate);
        assertTrue(IDs == null);
    }

    /*
     *   Checks so a roomID is returned if a room of tpye Single is available 
     *    on the specified dates
     */
    @Test
    public void testGetAvailableRoomOfSpecificTypeSingleWorking() {
        Date arrivalDate = Date.valueOf("2014-01-27");
        Date departureDate = Date.valueOf("2014-01-29");
        List<Integer> IDs = controller.getAvailableRoomIDsOfTypeBetweenDates("single", arrivalDate, departureDate);
        assertTrue(IDs.get(0) == 101);
    }

    /*
     *   Checks so a roomID is returned if a room of type Double is available 
     *    on the specified dates
     */
    @Test
    public void testGetAvailableRoomOfSpecificTypeDoubleRoomWorking() {
        Date arrivalDate = Date.valueOf("2020-03-02");
        Date departureDate = Date.valueOf("2020-03-04");
        List<Integer> IDs = controller.getAvailableRoomIDsOfTypeBetweenDates("double", arrivalDate, departureDate);
        assertTrue(IDs.get(0) == 100);
    }

    /*
     *   Checks so -1 is returned if no rooms exist in the database
     */
    @Test
    public void testGetAvailableRoomOfSpecificTypeDoubleRoomNoRoomsInDB() {
        EmptyDBFixture.setUp(con);
        Date arrivalDate = Date.valueOf("2015-03-02");
        Date departureDate = Date.valueOf("2015-03-04");
        List<Integer> IDs = controller.getAvailableRoomIDsOfTypeBetweenDates("double", arrivalDate, departureDate);
        assertTrue(IDs == null);
    }

    /*
     *   Checks so getAllCurrentGuests returns the correct amount of customers
     */
    @Test
    public void testGetAllCurrentGuests() {
        Map<Customer, Integer> guests = controller.getAllCurrentGuests();
        assertTrue(guests.size() == 1);
    }

    /*
     *   Checks so getAllFacilityNames returns the correct amount of facility names
     */
    @Test
    public void testGetAllFacilityNames() {
        List<String> facilityNames = controller.getAllFacilityNames();
        assertTrue(facilityNames.size() == 2);
    }

    /*
     * Tests so getFacility returns the correct facility
     */
    @Test
    public void testGetFacilityMatchingName() {
        List<String> facilityNames = controller.getAllFacilityNames();
        String expectedResult = facilityNames.get(0);
        Facility f = controller.getFacility(facilityNames.get(0));
        String result = f.getName();
        assertEquals(expectedResult, result);
    }

    /*
     *   Checks so getFacility method retursn null if bad name is given
     */
    @Test
    public void testGetFacilityNotMatchingName() {
        controller.getAllFacilityNames();
        Facility f = controller.getFacility("Bullcrap Name");
        assertNull(f);
    }

    /*
     * Tests so true is returend if date+timeslot is not booked
     */
    @Test
    public void testCheckAvailableFacilityBookingAvailable() {
        FacilityBooking fb = new FacilityBooking(1, 1, Date.valueOf("2099-01-01"), 
                1, 999, true);
        boolean status = controller.checkAvailableFacilityBooking(fb);
        assertTrue(status);
    }

    /*
     * Tests so false is returend if date+timeslot is not booked
     */
    @Test
    public void testCheckAvailableFacilityBookingUnavailable() {
        FacilityBooking fb = new FacilityBooking(1, 2, Date.valueOf("2014-03-24"), 
                4, 999, true);

        boolean status = controller.checkAvailableFacilityBooking(fb);
        assertFalse(status);
    }

    /*
     * Tests so true is returned when the user has a booking on date+timeslot
     */
    @Test
    public void testDoesUserHaveFacilityBookingOnSpecificDateAndTimeslotMatch() {
        boolean status
                = controller.doesUserHaveFacilityBookingOnSpecificDateAndTimeslot(
                        Date.valueOf("2014-03-24"), 1, 2);
        assertTrue(status);
    }

    /*
     * Tests so false is returned when the user doesn't have booking on date+timeslot
     */
    @Test
    public void testDoesUserHaveFacilityBookingOnSpecificDateAndTimeslotNoMatch() {
        boolean status
                = controller.doesUserHaveFacilityBookingOnSpecificDateAndTimeslot(
                        Date.valueOf("2099-03-24"), 1, 2);
        assertFalse(status);
    }

    /*
     *   Checks so GetAllUserIDs returns the correct amount of IDs
     */
    @Test
    public void testGetAllUserIDs() {
        List<Integer> userIDs = controller.getAllUserIDs();
        assertTrue(userIDs.size() == 3);
    }
}
