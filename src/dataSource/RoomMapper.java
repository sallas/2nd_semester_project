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
        List<Room> rooms = search(ID, "id");
        if (rooms.isEmpty()) {
            return null;
        } else {
            return rooms.get(0);
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

    @Override
    public List<Room> search(Object variable, String columnName) {
        List<Room> rooms = executeQueryAndGatherResults(
                Room.class,
                "SELECT * FROM room "
                + "where " + columnName + " = ?",
                "Fail in RoomMapper - search",
                new String[]{"ID", "type"},
                new int[]{DataType.INT, DataType.STRING},
                variable);
        return rooms;
    }
}
