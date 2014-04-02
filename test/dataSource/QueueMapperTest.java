package dataSource;

import domain.QueueEntry;
import java.sql.Connection;
import java.util.List;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;

public class QueueMapperTest {
    Connection con;
    QueueMapper qm;
    TestDBConnector connector = new TestDBConnector();

    
    public QueueMapperTest() {
    }
    
    @Before
    public void init() {
        con = connector.getConnection();
        ReservationFixture.setUp(con);
        qm = new QueueMapper(con);
    }
    
    @After
    public void tearDown() {
        connector.releaseConnection(con);
    }
    
    @Test
    public void testGetQueueForSpecificBooking(){
        List<QueueEntry> r = qm.getQueueForSpecificBooking(1);
        assertEquals(1, r.size());
    }
    
    @Test
    public void testGetQueueForSpecificUser(){
        List<QueueEntry> r = qm.getQueueForSpecificUser(2);
        assertEquals(1, r.size());
    }
    
    @Test
    public void testSaveQueueEntry(){
        boolean r = qm.saveQueueEntry(
                new QueueEntry(-1, 1, 2));
        List<QueueEntry> check = qm.getQueueForSpecificBooking(2);
        assertTrue(r);
        assertEquals(2, check.get(0).getID());
    }
    
    @Test
    public void testDeleteQueueEntryForSpecificID(){
        boolean r = qm.deleteQueueEntryForSpecificID(1, 2);
        List<QueueEntry> check = qm.getQueueForSpecificBooking(1);
        assertTrue(r);
        assertEquals(0, check.size());
    }

}