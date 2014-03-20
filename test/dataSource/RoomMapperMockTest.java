package dataSource;

import MockObjects.RoomMapperMock;
import domain.Room;
import java.util.HashMap;
import java.util.Map;
import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;

public class RoomMapperMockTest {

    DBFacade dbf;

    Room roomOne = new Room(100, "double");
    Room roomTwo = new Room(101, "double");
    Room roomThree = new Room(102, "single");

    @Before
    public void setUp() {
        RoomMapperMock rmm = new RoomMapperMock();
        //dbf = new DBFacade(rmm);
        Map<Integer, Room> rooms = new HashMap<>();
        rooms.put(roomOne.getID(), roomOne);
        rooms.put(roomTwo.getID(), roomTwo);
        rooms.put(roomThree.getID(), roomThree);
        rmm.setRooms(rooms);

    }

    @Test
    public void testGetRoomExistingRoomID() {
//        int ID = 100;
//        Room expResult = roomOne;
//        Room result = dbf.getRoom(ID);
//        assertEquals(expResult.toString(), result.toString());
    }

}
