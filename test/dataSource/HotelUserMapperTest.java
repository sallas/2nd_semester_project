package dataSource;

import domain.HotelUser;
import java.sql.Connection;
import java.util.List;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;

public class HotelUserMapperTest {

    Connection con;
    HotelUserMapper hum;
    TestDBConnector connector = new TestDBConnector();

    @Before
    public void init() {
        con = connector.getConnection();
        ReservationFixture.setUp(con);
        hum = new HotelUserMapper(con);
    }

    @After
    public void tearDown() {
        connector.releaseConnection(con);
    }

    @Test
    public void testGetAllUsers() {
        List<HotelUser> users = hum.getAllUsers();
        assertTrue(users.size() == 2);
    }

    @Test
    public void testGetUser() {
        List<HotelUser> users = hum.getUser(1);
        assertTrue(users.get(0).getUsername().equals("user1"));
    }
}
