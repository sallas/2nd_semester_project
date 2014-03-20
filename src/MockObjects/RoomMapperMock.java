package MockObjects;

import domain.Room;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class RoomMapperMock {

    private Map<Integer, Room> rooms;

    public RoomMapperMock() {
        rooms = new HashMap<>();
    }

    public void setRooms(Map<Integer, Room> rooms) {
        this.rooms = rooms;
    }

    public Room getRoom(int roomID) {
        return new Room(1, "Double");
    }

    public void addRoom(Room room) {
        rooms.put(room.getID(), room);
    }

    public List<Room> getAllRooms() {
        List<Room> returnRooms = new ArrayList<>();
        for (Map.Entry<Integer, Room> entry : rooms.entrySet()) {
            returnRooms.add(entry.getValue());
        }
        return returnRooms;
    }
}
