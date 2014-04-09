package dataSource;

import domain.Customer;
import domain.Facility;
import domain.FacilityBooking;
import domain.HotelUser;
import domain.InstructorBooking;

import domain.QueueEntry;
import domain.Reservation;
import domain.Room;
import domain.UnpaidReservation;
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
    private static InstructorBookingMapper instructorBookingMapper;
    private static Connection connection;
    private static AdministratorMapper admin;

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
        admin = new AdministratorMapper(con);
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
                queueMapper, facilityBookingMapper);
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

    public boolean addAccountf(HotelUser a) {
        return admin.addAccount(a);
    }

    public boolean deleteAccountf(HotelUser a) {
        return admin.deleteAccount(a);
    }

    public boolean AddSportFacilityf(Facility f) {
        return admin.addSportFacility(f);
    }

    public boolean DeleteSportFacilityf(Facility f) {
        return admin.deleteSportFacility(f);
    }

    public boolean updateUsernamef(HotelUser a) {
        return admin.updateUername(a);
    }

    public boolean updatePasswordf(HotelUser a) {
        return admin.updatePassword(a);
    }

    public boolean updateAddressf(Customer a) {
        return admin.updateAddress(a);
    }

    public boolean updateCountryf(Customer a) {
        return admin.updateCountry(a);
    }

    public boolean updateFirst_namef(Customer a) {
        return admin.updateFirst_name(a);
    }

    public boolean updateLast_namef(Customer a) {
        return admin.updateLast_name(a);
    }

    public boolean updatePhonef(Customer a) {
        return admin.updatePhone(a);
    }

    public boolean updateEmailf(Customer a) {
        return admin.updateEmail(a);
    }

    public boolean updateTravel_angencyf(Customer a) {
        return admin.updateTravel_agency(a);
    }

    public boolean updateReservation_idf(HotelUser a) {
        return admin.updateReservation_id(a);
    }

    public boolean updateSpentf(HotelUser a) {
        return admin.updateSpent(a);
    }

    public boolean updateStatus(HotelUser a) {
        return admin.updateStatus(a);
    }

}


