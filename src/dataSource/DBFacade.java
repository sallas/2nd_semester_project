package dataSource;

import domain.Customer;
import domain.Facility;
import domain.FacilityBooking;
import domain.HotelUser;
import domain.InstructorBooking;
import domain.Nortification;
import domain.QueueEntry;
import domain.Reservation;
import domain.Room;
import domain.UnpaidReservation;
import java.sql.Connection;
import java.sql.Date;
import java.sql.SQLException;
import java.util.ArrayList;
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
    private static InstructorBookingMapper instructorBookingMapper;
    private static ReservationCustomerMapper resCustomerMapper;
    private static NortificationsMapperInterface nortificationMapper;
    private static Connection connection;
    private static AdministratorMapper admin;
    private static UnitOfWorkProcess uow;
    private static LoginMapper log;

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
        instructorBookingMapper = new InstructorBookingMapper(con);
        resCustomerMapper = new ReservationCustomerMapper(con);
        nortificationMapper = new NortificationsMapper(con);
        admin = new AdministratorMapper(con);
        log = new LoginMapper(con);
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

    public boolean saveReservationInformation(Reservation r, List<Customer> c) {
        return SaveRegistrationLogic.saveReservationInformation(
                r, c, connection, reservationMapper, customerMapper,
                resCustomerMapper);
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
            Date date, int timeslot, int facilityID) {
        return facilityBookingMapper.getAllBookingsOfSpecificDateTimeslotFacility(
                date, timeslot, facilityID);
    }

    public HotelUser getUser(int userID) {
        List<HotelUser> hul = hotelUserMapper.getUser(userID);
        if (hul.isEmpty()) {
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

    public List<UnpaidReservation> getAllUnpaidReservationIDs() {
        return reservationMapper.getAllUnpaidReservationIDs();
    }

    public boolean removeUnpaidReservation(int ID) {
        return reservationMapper.removeUnpaidReservation(ID);
    }

    public List<Facility> getFacilityByID(int ID) {
        return facilityMapper.getFacilityByID(ID);
    }

    public boolean removeReservation(int ID) {
        return deleteReservationLogic.deleteReservation(
                ID, connection, reservationMapper, hotelUserMapper,
                queueMapper, facilityBookingMapper, resCustomerMapper);
    }

    public Date getUnpaidReservationBookingDateByID(int ID) {
        return reservationMapper.getUnpaidReservationBookingDateByID(ID);
    }

    public boolean removeHotelUserByReservationID(int ID) {
        return hotelUserMapper.removeHotelUserByReservationID(ID);
    }

    public List<Reservation> searchReservation(Object variable, String columnName) {
        return reservationMapper.search(variable, columnName);
    }

    public boolean saveQueueEntry(QueueEntry entry) {
        return queueMapper.saveQueueEntry(entry);
    }

    public void shutdown() {
        try {
            connection.close();
        } catch (SQLException e) {
            System.err.println(e);
        }
    }

    public List<Room> searchRoom(Object variable, String columnName) {
        return roomMapper.search(variable, columnName);
    }

    public List<HotelUser> searchHotelUser(Object variable, String columnName) {
        return hotelUserMapper.search(variable, columnName);
    }

    public boolean updateFacilityBookingUserID(int bookingID, int userID) {
        return facilityBookingMapper.updateFacilityBookingUserID(bookingID, userID);
    }

    public List<Customer> searchCustomer(Object variable, String columnName) {
        return customerMapper.search(variable, columnName);
    }

    public boolean deleteQueueEntryForSpecificID(int bookingID, int userID) {
        return queueMapper.deleteQueueEntryForSpecificID(bookingID, userID);
    }

    public List<FacilityBooking> searchFacilityBooking(Object variable, String columnName) {
        return facilityBookingMapper.search(variable, columnName);
    }

    public List<Facility> searchFacility(Object variable, String columnName) {
        return facilityMapper.search(variable, columnName);
    }

    public List<InstructorBooking> getInstructorBookings(int userID) {
        return instructorBookingMapper.getInstructorBookingByUserID(userID);
    }

    public List<Nortification> getAllNortifications() {
        return nortificationMapper.getAllNortifications();
    }

    public List<InstructorBooking> getInstructorBookingByUserIDAndDate(
            int userID, Date date) {
        return instructorBookingMapper.getInstructorBookingByUserIDAndDate(
                userID, date);
    }
    public boolean deleteAllNortifications() {
        return nortificationMapper.deleteAllNortifications();
    }

    public FacilityBooking getFacilityBookingOfSpecificID(int bookingID) {
        List<FacilityBooking> fbl = 
                facilityBookingMapper.getAllBookingsOfSpecificID(bookingID);
        if (fbl.isEmpty()){
            return null;
        } else {
            return fbl.get(0);
        }
    }

    public boolean saveNortification(Nortification n) {
        return nortificationMapper.saveNortification(n);
    }

   

    public boolean updateUsernamef(HotelUser a) {
        ArrayList<HotelUser> temp = new ArrayList<>();//temporary Arraylist for the update
        temp.add(a);
        return admin.updateUsername(temp);
    }

    public boolean updatePasswordf(HotelUser a) {
        ArrayList<HotelUser> temp = new ArrayList<>();//temporary Arraylist for the update
        temp.add(a);
        return admin.updatePassword(temp);
    }

 
    public boolean updateStatusf(HotelUser a) {
        ArrayList<HotelUser> temp = new ArrayList<>();//temporary Arraylist for the update
        temp.add(a);
        return admin.updateStatus(temp);
    }
    


    public void registerDirtyHotelUser(HotelUser hotelUser) {
        if (uow != null) {
            uow.registerDirtyHotelUser(hotelUser);
        }
    }

    public void startProcessHotelUserTransaction() {
        uow = new UnitOfWorkProcess(admin);
    }


    public HotelUser checkCredentials(String username, String password) {
        return log.getUsernameAndPassword(username, password);
    }

    public void lockHOtelUser() {
        admin.lockHotelUser();
    }

}
