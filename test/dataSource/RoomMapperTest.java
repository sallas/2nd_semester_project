package dataSource;

import domain.Room;
import java.sql.*;
import java.util.List;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;

public class RoomMapperTest {

    Connection con;
    RoomMapper rm;
    TestDBConnector connector = new TestDBConnector();

    /*
     * Sets up room mapper with our connection
     */
    @Before
    public void init() {
        con = connector.getConnection();
        ReservationFixture.setUp(con);
        rm = new RoomMapper(con);
    }

    @After
    public void tearDown() throws Exception {
        connector.releaseConnection(con);
    }

    /*
     * Checks so the correct room is returned by the mapper
     */
    @Test
    public void testGetRoomMatch() {
        Room r = rm.getRoom(100);
        assertTrue(r != null);
        assertTrue(r.getID() == 100);
    }

    /*
     * Checks so no room is returned when bad ID is given
     */
    @Test
    public void testGetReservationNoMatch() {
        Room r = rm.getRoom(9999);
        assertTrue(r == null);
    }

    /*
     * Checks so the correct amount of rooms are returned
     */
    @Test
    public void testGetAllRooms() {
        List<Room> rooms = rm.getAllRooms();
        assertEquals(2, rooms.size());
    }

    /*
     *   Checks so no rooms are returend when the DB is empty
     */
    @Test
    public void testGetAllRoomsNoRoomsInDB() {
        EmptyDBFixture.setUp(con);
        List<Room> rooms = rm.getAllRooms();
        assertEquals(0, rooms.size());
    }

    /*
     *   Checks so the correct date is returned
     */
    @Test
    public void testGetRoomAvailabilityDateMatchingID() {
        Date date = rm.getRoomAvailabilityDate(101);
        Date expectedDate = Date.valueOf("2014-02-05");
        assertTrue(expectedDate.equals(date));
    }

    /*
     *   Checks so null is returned when a none existing Room ID is given
     */
    @Test
    public void testGetRoomAvailabilityDateNoMatchingID() {
        Date date = rm.getRoomAvailabilityDate(99);
        assertTrue(date == null);
    }

    /*
     *   Checks so null is returend when there are no rooms in the DB
     */
    @Test
    public void testGetRoomAvailabilityDateNoRoomsInDB() {
        EmptyDBFixture.setUp(con);
        Date date = rm.getRoomAvailabilityDate(100);
        assertTrue(date == null);
    }

}
