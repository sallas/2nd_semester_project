package dataSource;

import domain.InstructorBooking;
import java.sql.Connection;
import java.util.List;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;

public class InstructorBookingMapperTest {

    private Connection con;
    private InstructorBookingMapper ibm;
    private TestDBConnector connector = new TestDBConnector();

    /*
     * Sets up instructor mapper with our connection
     */
    @Before
    public void init() {
        con = connector.getConnection();
        ReservationFixture.setUp(con);
        ibm = new InstructorBookingMapper(con);
    }

    @After
    public void tearDown() throws Exception {
        connector.releaseConnection(con);
    }

    @Test
    public void testSearchMatch() {
        List<InstructorBooking> ib = ibm.search(1, "id");
        assertTrue(ib.get(0).getID() == 1);
    }

    @Test
    public void testSearchNoMatch() {
        List<InstructorBooking> instructors = ibm.search(-1, "id");
        assertTrue(instructors.isEmpty());
    }

}