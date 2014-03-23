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

}
