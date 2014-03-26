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

public class RoomMapper implements RoomMapperInterface {

    private final Connection con;

    public RoomMapper(Connection con) {
        this.con = con;
    }

    //This method returns a room object based on the ID that's being given to the method as a parameter.
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

    //This method returns a list of all the room objects.
    @Override
    public List<Room> getAllRooms() {
        System.out.println("Start of all Rooms");
        List<Room> allRooms = new ArrayList();
        String SQLString = "select *"
                + " from room";
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
        System.out.println("allRooms: " + allRooms.size());
        return allRooms;
    }

    @Override
    public Date getRoomAvailabilityDate(int ID) {
        Date date = null;
        int number_nights = 0;
        String SQLString = "select checkin_date, number_nights"
                + " from reservation "
                + "where room_id = ? AND checkin_date = "
                + "(select max(checkin_date) "
                + "from reservation "
                + "where room_ID = ?)";
        PreparedStatement statement = null;
        try {
            
            statement = con.prepareStatement(SQLString);
            statement.setInt(1, ID);
            statement.setInt(2, ID);
            ResultSet rs = statement.executeQuery();
            if (rs.next()) {
                date = rs.getDate(1);
                number_nights = rs.getInt(2);
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
        
        if(date == null)
            return null;
        
        Calendar availabilityDate = Calendar.getInstance();
        availabilityDate.setTimeInMillis(date.getTime());
        availabilityDate.add(Calendar.DAY_OF_MONTH, number_nights);
        date.setTime(availabilityDate.getTimeInMillis());
        
        return date;
    }
}
