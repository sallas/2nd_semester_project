package dataSource;

import domain.Facility;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import org.junit.After;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;
import org.junit.Before;
import org.junit.Test;

public class FacilityMapperTest {
    
    Connection con;
    FacilityMapper fm;
    TestDBConnector connector = new TestDBConnector();
    
    public FacilityMapperTest() {
    }
    
    @Before
    public void init() {
        con = connector.getConnection();
        ReservationFixture.setUp(con);
        fm = new FacilityMapper(con);
    }
    
    @After
    public void tearDown() {
        connector.releaseConnection(con);
    }

    @Test
    public void testGetAllFacilities() throws SQLException {
        ArrayList<Facility> res = fm.getFacilities();
        assertEquals(res.get(0).getID(), 1);
        assertEquals(res.get(1).isHasBooking(), false);
        assertEquals(res.get(1).isHasInstructor(), true);
    }
    
    @Test
    public void testGetFacilityByType() throws SQLException {
        ArrayList<Facility> res = fm.getFacilities("tennis");
        assertEquals(res.size(), 1);
        assertEquals(res.get(0).getCapacity(), 4);
    }
    
    @Test
    public void testGetFacilityByNonExistingType() throws SQLException {
        ArrayList<Facility> res = fm.getFacilities("tenn");
        assertEquals(res.size(), 0);
    }
}
