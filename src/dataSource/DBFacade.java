package dataSource;

import domain.Customer;
import domain.Reservation;
import domain.Room;
import java.sql.Date;
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

    public DBFacade(ReservationMapperInterface resMapper, CustomerMapperInterface cusMapper) {
        reservationMapper = resMapper;
        customerMapper = cusMapper;
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
        System.out.println("In the facade, about to go to roomMapper.getAllRooms()");
        return roomMapper.getAllRooms();
    }

    public boolean saveReservationInformation(Reservation r, Customer c) {
        int customerID = customerMapper.saveNewCustomer(c);
        if (customerID == -1) {
            return false;
        }
        r.setCustomerID(customerID);
        return reservationMapper.saveReservation(r);
    }

    public Customer getCustomer(int ID) {
        return customerMapper.getCustomer(ID);
    }

    public Date getRoomAvailabilityDate(int ID) {
        return roomMapper.getRoomAvailabilityDate(ID);
    }
    
    public List<Reservation> getAllReservationsOfSpecificType(String type) {
        return reservationMapper.getAllReservationsOfSpecificType(type);
    }
    
}
