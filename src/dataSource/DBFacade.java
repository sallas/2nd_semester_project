package dataSource;

import domain.Customer;
import domain.Reservation;
import domain.Room;
import java.util.List;

public class DBFacade {

    private static ReservationMapperInterface reservationMapper;
    private static RoomMapperInterface roomMapper;
    private static DBFacade instance;
    private static CustomerMapperInterface customerMapper;

    private DBFacade() {
        reservationMapper = new ReservationMapper(DBConnector.getConnection());
        roomMapper = new RoomMapper(DBConnector.getConnection());
        customerMapper = new CustomerMapper(DBConnector.getConnection());
    }

    public DBFacade(ReservationMapperInterface resMapper) {
        reservationMapper = resMapper;
    }

    public DBFacade(RoomMapperInterface rmMapper) {
        roomMapper = rmMapper;
    }

    public static DBFacade getInstance() {
        if (instance == null) {
            instance = new DBFacade();
        }
        return instance;
    }

    public Reservation getReservation(int ID) {
        return reservationMapper.getReservation(ID);
    }

    public Room getRoom(int ID) {
        return roomMapper.getRoom(ID);
    }

    public List<Room> getAllRooms() {
        return roomMapper.getAllRooms();
    }

    public boolean saveReservationInformation(Reservation r, Customer c) {
        int customerID = customerMapper.saveNewCustomer(c);
        if(customerID == 0)
            return false;
        r.setCustomerID(customerID);
        return reservationMapper.saveReservation(r);
    }
}
