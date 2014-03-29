package dataSource;

import domain.Customer;
import domain.Facility;
import domain.FacilityBooking;
import domain.Reservation;
import domain.Room;
import java.sql.Connection;
import java.sql.Date;
import java.sql.SQLException;
import java.util.List;

public class DBFacade {

    private static ReservationMapperInterface reservationMapper;
    private static RoomMapperInterface roomMapper;
    private static DBFacade instance;
    private static CustomerMapperInterface customerMapper;
    private static FacilityMapperInterface facilityMapper;
    private static FacilityBookingMapper facilityBookingMapper;
    private static Connection connection;

    private DBFacade() {
        connection = DBConnector.getConnection();
        reservationMapper = new ReservationMapper(connection);
        roomMapper = new RoomMapper(connection);
        customerMapper = new CustomerMapper(connection);
        facilityMapper = new FacilityMapper(connection);
        facilityBookingMapper = new FacilityBookingMapper(connection);
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
        return roomMapper.getAllRooms();
    }

    public boolean saveReservationInformation(Reservation r, Customer c) {
         return SaveRegistrationLogic.saveReservationInformation(
                r, c, connection, reservationMapper, customerMapper);
    }

    public Customer getCustomer(int ID) {
        return customerMapper.getCustomer(ID);
    }

    public List<Reservation> getAllReservationsOfSpecificType(String type) {
        return reservationMapper.getAllReservationsOfSpecificType(type);
    }

    public List<Customer> getAllCustomers() {
        return customerMapper.getAllCustomers();
    }

    public List<Reservation> getAllReservations() {
        return reservationMapper.getAllReservations();
    }

    public boolean checkAvailableReservation(Reservation r) {
        return reservationMapper.checkAvailableReservation(r);
    }

    public void lockReservationTable() {
        reservationMapper.lockReservationTable();
    }
    
    public List<Facility> getAllFacilities() {
        return facilityMapper.getFacilities();
    }
    
    public boolean checkAvailableFacilityBooking(FacilityBooking fb) {
        return facilityBookingMapper.checkAvailableFacilityBooking(fb);
    }
    
    public boolean saveFacilityBooking(FacilityBooking fb) {
        return facilityBookingMapper.saveFacilityBooking(fb);
    }
    
    public List<FacilityBooking> getAllFacilityBookingsOfSpecificDate(Date date) {
        return facilityBookingMapper.getAllBookingsOfSpecificDate(date);
    }
    
    public List<FacilityBooking> getAllFacilityBookingsOfSpecificDateAndUser(Date date, int id) {
        return facilityBookingMapper.getAllBookingsOfSpecificDateAndUser(date, id);
    }
    
    public List<FacilityBooking> getAllFacilityBookingsOfSpecificDateTimeslotUser(
            Date date, int userID, int timeslot) {
        return facilityBookingMapper.getAllBookingsOfSpecificDateTimeslotUser(date, userID, timeslot);
    }
}
