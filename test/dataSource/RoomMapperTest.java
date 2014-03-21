package dataSource;

import java.sql.*;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;

public class RoomMapperTest {

    private Connection con;
    private TestDBConnector connector = new TestDBConnector();
    private RoomMapper rm;

    @Before
    public void init() {
        con = connector.getConnection();
        ReservationMapperFixture.setUp(con);
        rm = new RoomMapper(con);
    }

    @After
    public void tearDown() throws Exception {
        connector.releaseConnection(con);
    }

}
