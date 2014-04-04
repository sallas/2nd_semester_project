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

    /*
     * Test so the mapper returns the correct amount of HotelUsers
     */
    @Test
    public void testGetAllUsers() {
        List<HotelUser> users = hum.getAllUsers();
        assertTrue(users.size() == 3);
    }

    /*
     * Test so correct user is returend when using valid user ID
     */
    @Test
    public void testGetUserMatch() {
        List<HotelUser> users = hum.getUser(1);
        assertTrue(users.get(0).getUsername().equals("user1"));
    }

    /*
     * Test so no user is returend when using invalid user ID
     */
    @Test
    public void testGetUserNoMatch() {
        List<HotelUser> users = hum.getUser(-1);
        assertTrue(users.isEmpty());
    }
}
