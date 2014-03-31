package dataSource;

import domain.Room;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Calendar;
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
                new String[]{"ID", "Type"},
                new int[]{DataType.INT, DataType.STRING},
                ID);
        if (room.isEmpty()) {
            return null;
        } else {
            return room.get(0);
        }

//        
//        Room r = null;
//        String SQLString = "select * "
//                + "from room "
//                + "where id = ?";
//        PreparedStatement statement = null;
//
//        try {
//            statement = con.prepareStatement(SQLString);
//            statement.setInt(1, ID);
//            ResultSet rs = statement.executeQuery();
//            if (rs.next()) {
//                r = new Room(ID,
//                        rs.getString(2));
//            }
//        } catch (SQLException e) {
//            System.out.println("Fail in RoomMapper - getRoom");
//            System.out.println(e.getMessage());
//        } finally {
//            try {
//                if (statement != null) {
//                    statement.close();
//                }
//            } catch (SQLException e) {
//                System.out.println("Fail in RoomMapper - getRoom");
//                System.out.println(e.getMessage());
//            }
//        }
//        return r;
    }

    //This method returns a list of all the room objects.
    @Override
    public List<Room> getAllRooms() {
        
        ArrayList<Room> room = executeQueryAndGatherResults(
                Room.class,
                "SELECT * FROM room",
                "Fail in RoomMapper - getRoom",
                new String[]{"ID", "Type"},
                new int[]{DataType.INT, DataType.STRING});
        if (room.isEmpty()) {
            return null;
        } else {
            return room;
        }
        
        
        
//        
//        List<Room> allRooms = new ArrayList();
//        String SQLString = "select *"
//                + " from room";
//        PreparedStatement statement = null;
//        try {
//            statement = con.prepareStatement(SQLString);
//            ResultSet rs = statement.executeQuery();
//            while (rs.next()) {
//                allRooms.add(new Room(rs.getInt(1), rs.getString(2)));
//            }
//        } catch (SQLException e) {
//            System.out.println("Fail in RoomMapper - getAllRooms");
//            System.out.println(e.getMessage());
//        } finally {
//            try {
//                if (statement != null) {
//                    statement.close();
//                }
//            } catch (SQLException e) {
//                System.out.println("Fail in RoomMapper - getAllRooms");
//                System.out.println(e.getMessage());
//            }
//        }
//        return allRooms;
    }
}
