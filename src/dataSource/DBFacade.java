package dataSource;

import domain.Customer;
import domain.Facility;
import domain.FacilityBooking;
import domain.HotelUser;

import domain.QueueEntry;
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
    private static HotelUserMapperInterface hotelUserMapper;
    private static QueueMapperInterface queueMapper;
    private static Connection connection;

    private DBFacade() {
        connection = DBConnector.getConnection();
        instantiateMappers(connection);
    }

    public DBFacade(ReservationMapperInterface resMapper, CustomerMapperInterface cusMapper) {
        reservationMapper = resMapper;
        customerMapper = cusMapper;
    }

    private void instantiateMappers(Connection con) {
        reservationMapper = new ReservationMapper(con);
        roomMapper = new RoomMapper(con);
        customerMapper = new CustomerMapper(con);
        facilityMapper = new FacilityMapper(con);
        facilityBookingMapper = new FacilityBookingMapper(con);
        hotelUserMapper = new HotelUserMapper(con);
        queueMapper = new QueueMapper(con);
    }

    public DBFacade(Connection con) {
        connection = con;
        instantiateMappers(con);
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
    
    public List<FacilityBooking> getAllBookingsOfSpecificDateTimeslotFacility(
            Date date, int timeslot, int facilityID){
        return facilityBookingMapper.getAllBookingsOfSpecificDateTimeslotFacility(
                date, timeslot, facilityID);
    }

    public HotelUser getUser(int userID) {
        List<HotelUser> hul = hotelUserMapper.getUser(userID);
        if (hul.isEmpty()){
            return null;
        } else {
            return hul.get(0);
        }
    }

    public List<QueueEntry> getQueueEntriesOfSpecificBooking(int id) {
        return queueMapper.getQueueForSpecificBooking(id);
    }

    public boolean removeFacilityBooking(int ID) {
        return facilityBookingMapper.removeFacilityBooking(ID);
    }

    public List<FacilityBooking> getAllFacilityBookingOfSpecificUser(int ID) {
        return facilityBookingMapper.getAllFacilityBookingOfSpecificUser(ID);
    }
    
    public List<HotelUser> getAllUsers() {
        return hotelUserMapper.getAllUsers();
    } 
    
    public List<Facility> getFacilityByID(int ID) {
        return facilityMapper.getFacilityByID(ID);
    }
    
    public List<Reservation> searchReservation(Object variable, String columnName) {
        return reservationMapper.search(variable, columnName);
    }
}
