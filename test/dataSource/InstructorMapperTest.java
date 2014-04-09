package dataSource;

import domain.Instructor;
import java.sql.Connection;
import java.util.List;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;

public class InstructorMapperTest {

    private Connection con;
    private InstructorMapper im;
    private TestDBConnector connector = new TestDBConnector();

    /*
     * Sets up instructor mapper with our connection
     */
    @Before
    public void init() {
        con = connector.getConnection();
        ReservationFixture.setUp(con);
        im = new InstructorMapper(con);
    }

    @After
    public void tearDown() throws Exception {
        connector.releaseConnection(con);
    }

    @Test
    public void testSearchMatch() {
        List<Instructor> instructors = im.search(1, "id");
        assertTrue(instructors.get(0).getID() == 1);
    }

    @Test
    public void testSearchNoMatch() {
        List<Instructor> instructors = im.search(-1, "id");
        assertTrue(instructors.isEmpty());
    }

}
