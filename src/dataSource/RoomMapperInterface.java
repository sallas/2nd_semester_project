package dataSource;

import domain.Room;
import java.util.List;

public interface RoomMapperInterface {

    Room getRoom(int ID);

    List<Room> getAllRooms();
    
    List<Room> search(Object variable, String columnName);
    
}
