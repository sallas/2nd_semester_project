package MockObjects;

import dataSource.RoomMapperInterface;
import domain.Room;
import java.sql.Date;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class RoomMapperMock implements RoomMapperInterface {

    private Map<Integer, Room> rooms;

    public RoomMapperMock() {
        rooms = new HashMap<>();
    }

    public void setRooms(Map<Integer, Room> rooms) {
        this.rooms = rooms;
    }

    /*
     * Returns the room with key ID if it exists
     * otherwise it will return null
     */
    @Override
    public Room getRoom(int roomID) {
        Room r = null;
        if (rooms.containsKey(roomID)) {
            r = rooms.get(roomID);
        }
        return r;
    }

    public void addRoom(Room room) {
        rooms.put(room.getID(), room);
    }

    /*
     * Goes through the map object filling a list of room objects
     */
    @Override
    public List<Room> getAllRooms() {
        List<Room> returnRooms = new ArrayList<>();
        for (Map.Entry<Integer, Room> entry : rooms.entrySet()) {
            returnRooms.add(entry.getValue());
        }
        return returnRooms;
    }

    @Override
    public List<Room> search(Object variable, String columnName) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

}
