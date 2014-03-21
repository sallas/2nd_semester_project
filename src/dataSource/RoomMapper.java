package dataSource;

import domain.Room;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

public class RoomMapper implements RoomMapperInterface {

    private final Connection con;

    public RoomMapper(Connection con) {
        this.con = con;
    }

    @Override
    public Room getRoom(int ID) {
        Room r = null;
        String SQLString = "select * "
                + "from room "
                + "where id = ?";
        PreparedStatement statement = null;

        try {
            statement = con.prepareStatement(SQLString);
            statement.setInt(1, ID);
            ResultSet rs = statement.executeQuery();
            if (rs.next()) {
                r = new Room(ID,
                        rs.getString(2));
            }
        } catch (SQLException e) {
            System.out.println("Fail in RoomMapper - getRoom");
            System.out.println(e.getMessage());
        } finally {
            try {
                if (statement != null) {
                    statement.close();
                }
            } catch (SQLException e) {
                System.out.println("Fail in RoomMapper - getRoom");
                System.out.println(e.getMessage());
            }
        }
        return r;
    }

    @Override
    public List<Room> getAllRooms() {
        List<Room> allRooms = null;
        String SQLString = "select *"
                + "from room";
        PreparedStatement statement = null;
        try {
            statement = con.prepareStatement(SQLString);
            ResultSet rs = statement.executeQuery();
            while (rs.next()) {
                allRooms.add(new Room(rs.getInt(1), rs.getString(2)));
            }
        } catch (SQLException e) {
            System.out.println("Fail in RoomMapper - getAllRooms");
            System.out.println(e.getMessage());
        } finally {
            try {
                if (statement != null) {
                    statement.close();
                }
            } catch (SQLException e) {
                System.out.println("Fail in RoomMapper - getAllRooms");
                System.out.println(e.getMessage());
            }
        }
        return allRooms;
    }
}
