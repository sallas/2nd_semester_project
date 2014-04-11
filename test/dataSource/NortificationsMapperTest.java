package dataSource;

import domain.Nortification;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;
import org.junit.After;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import org.junit.Before;
import org.junit.Test;

public class NortificationsMapperTest {
        
    Connection con;
    NortificationsMapper nm;
    TestDBConnector connector = new TestDBConnector();
    
    public NortificationsMapperTest() {
    }
    
    @Before
    public void init() {
        con = connector.getConnection();
        ReservationFixture.setUp(con);
        nm = new NortificationsMapper(con);
    }
    
    @After
    public void tearDown() {
        connector.releaseConnection(con);
    }

    @Test
    public void testGetAllNortifications() throws SQLException {
        List<Nortification> res = nm.getAllNortifications();
        assertEquals(3, res.size());
    }
    
    @Test
    public void testSaveNortification() throws SQLException {
        boolean isSaved = nm.saveNortification(new Nortification(-1, "Hello"));
        List<Nortification> res = nm.getAllNortifications();
        assertTrue(isSaved);
        assertEquals("Hello", res.get(res.size()-1).getMessage());
    }
    
    @Test
    public void testDeleteAllNortifications() throws SQLException {
        boolean isDeleted = nm.deleteAllNortifications();
        List<Nortification> res = nm.getAllNortifications();
        assertTrue(isDeleted);
        assertEquals(0, res.size());
    }
}
