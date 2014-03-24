package dataSource;

import domain.Room;
import java.util.List;

public interface RoomMapperInterface {

    Room getRoom(int ID);

    List<Room> getAllRooms();
}
