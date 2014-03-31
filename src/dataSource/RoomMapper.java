package dataSource;

import domain.Room;
import java.sql.Connection;
import java.util.ArrayList;
import java.util.List;

public class RoomMapper extends AbstractMapper implements RoomMapperInterface {

    //private final Connection con;
    public RoomMapper(Connection con) {
        super(con);
    }

    //This method returns a room object based on the ID that's being given to the method as a parameter.
    @Override
    public Room getRoom(int ID) {

        ArrayList<Room> room = executeQueryAndGatherResults(
                Room.class,
                "SELECT * FROM room WHERE id = ?",
                "Fail in RoomMapper - getRoom",
                new String[]{"ID", "type"},
                new int[]{DataType.INT, DataType.STRING},
                ID);
        if (room.isEmpty()) {
            return null;
        } else {
            return room.get(0);
        }
    }

    //This method returns a list of all the room objects.
    @Override
    public List<Room> getAllRooms() {
        
        List<Room> room = executeQueryAndGatherResults(
                Room.class,
                "SELECT * FROM room",
                "Fail in RoomMapper - getRoom",
                new String[]{"ID", "type"},
                new int[]{DataType.INT, DataType.STRING});
        return room;
    }
}
