package dataSource;

import MockObjects.CustomerMapperMock;
import MockObjects.ReservationMapperMock;
import domain.Customer;
import domain.Reservation;
import java.sql.Date;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;

public class ReservationAndCustomerMapperMockTest {

    DBFacade dbf;
    // Creation of reservation to use in tests
    Reservation reservationOne
            = new Reservation(1, 100, 1, new Date(01, 01, 2014), new Date(05, 01, 2014));
    Reservation reservationTwo
            = new Reservation(2, 101, 2, new Date(01, 01, 2014), new Date(05, 01, 2014));
    Reservation reservationThree
            = new Reservation(3, 101, 1, new Date(01, 02, 2014), new Date(05, 02, 2014));
    Customer customerOne
            = new Customer(1, "address1", "country1", "firstName1", "lastName1",
                    "33", "email1", "agency1");
    Customer customerTwo
            = new Customer(2, "address2", "country2", "firstName2", "lastName2",
                    "332", "email2", "agency2");
    int customerSequenceNo = 3;
    int reservationSequenceNo = 4;

    final int IRRELEVANT = 1;
    /*
     * Sets up the two mock object before each set, handing it to the DBFacade
     * changes it behavior to use the mock object instead of the database
     */

    @Before
    public void init() {
        //Builds and fills the reservation mock object
        ReservationMapperMock resMock = new ReservationMapperMock();
        Map<Integer, Reservation> reservations = new HashMap<>();
        reservations.put(reservationOne.getID(), reservationOne);
        reservations.put(reservationTwo.getID(), reservationTwo);
        reservations.put(reservationThree.getID(), reservationThree);
        resMock.setReservations(reservations);
        resMock.setSequenceNo(reservationSequenceNo);
        List<Integer> roomID = Arrays.asList(100, 101);
        resMock.setRoomID(roomID);

        // Builds and fills the customer mock object
        CustomerMapperMock cusMock = new CustomerMapperMock();
        Map<Integer, Customer> customers = new HashMap<>();
        customers.put(customerOne.getID(), customerOne);
        customers.put(customerTwo.getID(), customerTwo);
        cusMock.setCustomers(customers);
        cusMock.setSequence(customerSequenceNo);

        dbf = new DBFacade(resMock, cusMock);
        //dbf = DBFacade.getInstance();
    }

    /*
     * Checks so the dbf return the reservation with the correct ID
     */
    @Test
    public void testGetReservationMatchingID() {
        int ID = 1;
        Reservation expResult = reservationOne;
        Reservation result = dbf.getReservation(ID);
        assertEquals(expResult.getID(), result.getID());
    }

    /*
     * Checks so no reservation is returned when bad ID is given
     */
    @Test
    public void testGetReservationNotMatchingID() {
        int ID = 99;
        Reservation result = dbf.getReservation(ID);
        assertTrue(result == null);
    }

    /*
     * Checks so the dbf return the customer with the correct ID
     */
    @Test
    public void testGetCustomerMatchingID() {
        int ID = 1;
        Customer expResult = customerOne;
        Customer result = dbf.getCustomer(ID);
        assertEquals(expResult.getID(), result.getID());
    }

    /*
     * Checks so no customer is returned when bad ID is given
     */
    @Test
    public void testGetCustomerNotMatchingID() {
        int ID = 99;
        Reservation result = dbf.getReservation(ID);
        assertTrue(result == null);
    }

    /*
     * Checks so the reservation is inserted when all needed info
     * is correct and inside the database(mock objects)
     */
    @Test
    public void testSaveReservationNoConflict() {
        Reservation reservation
                = new Reservation(IRRELEVANT, 101, IRRELEVANT, new Date(01, 01, 2014), new Date(01, 04, 2014));
        Customer customer = new Customer(IRRELEVANT, "address3", "country3", "firstName3", "lastName3",
                "333", "email3", "agency3");
        boolean status = dbf.saveReservationInformation(reservation, customer);
        assertTrue(status);
    }

    /*
     * Checks so the reservation is not inserted when the room ID doesn't
     * exist.
     */
    @Test
    public void testSaveReservationWithConflict() {
        Reservation reservation
                = new Reservation(IRRELEVANT, 999, IRRELEVANT, new Date(01, 01, 2014), new Date(01, 04, 2014));
        Customer customer = new Customer(IRRELEVANT, "address3", "country3", "firstName3", "lastName3",
                "333", "email3", "agency3");
        boolean status = dbf.saveReservationInformation(reservation, customer);
        assertFalse(status);
    }
}
