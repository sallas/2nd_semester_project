package dataSource;

import domain.Reservation;
import domain.Room;
import java.util.List;

public class DBFacade {

    private static ReservationMapperInterface reservationMapper;
    private static RoomMapperInterface roomMapper;
    private static DBFacade instance;

    private DBFacade() {
        reservationMapper = new ReservationMapper(DBConnector.getConnection());
        roomMapper = new RoomMapper(DBConnector.getConnection());
    }

    public DBFacade(ReservationMapperInterface resMapper) {
        reservationMapper = resMapper;
    }

    public DBFacade(RoomMapperInterface rmMapper) {
        roomMapper = rmMapper;
    }

    public static DBFacade getInstance() {
        if(instance == null)
            instance = new DBFacade();
        return instance;
    }

    public Reservation getReservation(int ID) {
        return reservationMapper.getReservation(ID);
    }

    public boolean saveReservatoin(Reservation r) {
        return reservationMapper.saveReservation(r);
    }

    public Room getRoom(int ID) {
        return roomMapper.getRoom(ID);
    }

    public List<Room> getAllRooms() {
        return roomMapper.getAllRooms();
    }
}
