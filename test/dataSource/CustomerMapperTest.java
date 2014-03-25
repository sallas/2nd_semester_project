package dataSource;

import domain.Customer;
import java.sql.Connection;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;

public class CustomerMapperTest {

    Connection con;
    CustomerMapper cm;
    TestDBConnector connector = new TestDBConnector();

    @Before
    public void init() {
        con = connector.getConnection();
        ReservationFixture.setUp(con);
        cm = new CustomerMapper(con);
    }

    @After
    public void tearDown() {
        connector.releaseConnection(con);
    }

    /*
     * Checks so the correct customer is returned by the mapper
     */
    @Test
    public void testGetReservationMatch() {
        Customer c = cm.getCustomer(1);
        assertTrue(c != null);
        assertTrue(c.getID() == 1);
    }

    /*
     * Checks so no customer is returned when bad ID is given
     */
    @Test
    public void testGetReservationNoMatch() {
        Customer c = cm.getCustomer(9999);
        assertTrue(c == null);
    }

    /*
     * Checks so the customer is inserted and the correct ID is returned
     */
    @Test
    public void testSaveReservationNewID() {
        Customer customerOne
                = new Customer(111, "address1", "country1", "firstName1", "lastName1",
                        "33", "email1", "agency1");
        int customerID = cm.saveNewCustomer(customerOne);
        assertTrue(customerID == 3);
    }
    
    /*
     * Checks so the customer is not inserted when address is null
     */
    @Test
    public void testSaveReservationAddressEqualNull() {
        Customer customerOne
                = new Customer(111, null, "country1", "firstName1", "lastName1",
                        "33", "email1", "agency1");
        int customerID = cm.saveNewCustomer(customerOne);
        assertTrue(customerID == -1);
    }
    
     /*
     * Checks so the customer is not inserted when firstName is null
     */
    @Test
    public void testSaveReservationFirstNameEqualNull() {
        Customer customerOne
                = new Customer(111, "address", "country1", null, "lastName1",
                        "33", "email1", "agency1");
        int customerID = cm.saveNewCustomer(customerOne);
        assertTrue(customerID == -1);
    }
    
     /*
     * Checks so the customer is not inserted when last name is null
     */
    @Test
    public void testSaveReservationLastNameEqualNull() {
        Customer customerOne
                = new Customer(111, "address", "country1", "firstName1", null,
                        "33", "email1", "agency1");
        int customerID = cm.saveNewCustomer(customerOne);
        assertTrue(customerID == -1);
    }
    
     /*
     * Checks so the customer is not inserted when country is null
     */
    @Test
    public void testSaveReservationCountryEqualNull() {
        Customer customerOne
                = new Customer(111, "address", null, "firstName1", "lastName1",
                        "33", "email1", "agency1");
        int customerID = cm.saveNewCustomer(customerOne);
        assertTrue(customerID == -1);
    }
    
     /*
     * Checks so the customer is not inserted when email is null
     */
    @Test
    public void testSaveReservationEmailEqualNull() {
        Customer customerOne
                = new Customer(111, "address", "country1", "firstName1", "lastName1",
                        "33", null, "agency1");
        int customerID = cm.saveNewCustomer(customerOne);
        assertTrue(customerID == -1);
    }
    
     /*
     * Checks so the customer is not inserted when phone number is null
     */
    @Test
    public void testSaveReservationPhoneNumEqualNull() {
        Customer customerOne
                = new Customer(111, "address", "country1", "firstName1", "lastName1",
                        null, "email1", "agency1");
        int customerID = cm.saveNewCustomer(customerOne);
        assertTrue(customerID == -1);
    }
    
     /*
     * Checks so the customer is inserted when agency is null
     */
    @Test
    public void testSaveReservationLastAgencyEqualNull() {
        Customer customerOne
                = new Customer(111, "address", "country1", "firstName1", "lastName1",
                        "33", "email1", null);
        int customerID = cm.saveNewCustomer(customerOne);
        assertTrue(customerID == 3);
    }

}
