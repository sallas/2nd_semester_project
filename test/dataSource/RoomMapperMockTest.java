package dataSource;

import MockObjects.RoomMapperMock;
import domain.Room;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;

public class RoomMapperMockTest {

    DBFacade dbf;
    // builds rooms used in the mock object
    Room roomOne = new Room(100, "double");
    Room roomTwo = new Room(101, "double");
    Room roomThree = new Room(102, "single");

    /*
     * Sets up our mock object with the rooms created before
     * creats the DBFacade with our mock object
     */
    @Before
    public void setUp() {
        RoomMapperMock rmm = new RoomMapperMock();
        dbf = new DBFacade(rmm);
        Map<Integer, Room> rooms = new HashMap<>();
        rooms.put(roomOne.getID(), roomOne);
        rooms.put(roomTwo.getID(), roomTwo);
        rooms.put(roomThree.getID(), roomThree);
        rmm.setRooms(rooms);

    }

    /*
     * Checks so the dbf return the room with the correct ID
     */
    @Test
    public void testGetRoomExistingRoomID() {
        int ID = 100;
        Room expResult = roomOne;
        Room result = dbf.getRoom(ID);
        assertEquals(expResult.toString(), result.toString());
    }
    
    /*
     * Checks so the dbf returns null when trying to find a none 
     * existing room
     */
    @Test
    public void testGetRoomNoneExistingRoomID() {
        int ID = 9999;
        Room r = dbf.getRoom(ID);
        assertTrue(r == null);
    }
    
    @Test
    public void testGetAllRooms()
    {
        List<Room> rooms = dbf.getAllRooms();
        assertEquals(3, rooms.size());
    }

}
