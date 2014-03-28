package dataSource;
import domain.FacilityBooking;
import domain.Reservation;
import java.sql.Connection;
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
}