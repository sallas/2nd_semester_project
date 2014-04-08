package domain;

import dataSource.DBFacade;
import java.sql.Connection;
import java.sql.Date;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Controller {

    private DBFacade facade;
    private EmailValidator emailValidator;
    private static Controller instance = null;
    private int currentRoomID;
    private Map<String, Facility> facilityMap;

    /*
     * Constructor only used for testing purposes
     * NEEDS TO BE REMOVED BEFORE DEPLOYMENT
     */
    protected Controller(Connection con) {
        facade = new DBFacade(con);
        emailValidator = new EmailValidator();
        facilityMap = new HashMap<>();
    }

    /*
     *   Returns a List of Strings containing information about all rooms
     *   most specificly if they are available or not
     */
    public List<String> getRooms() {
        List<String> roomList = new ArrayList();
        List<Room> allRooms = facade.getAllRooms();
        java.util.Date utilDate = new java.util.Date();
        java.sql.Date currentDate = new java.sql.Date(utilDate.getTime());
        List<Integer> availableSingleRooms = getAllAvailableRoomIDsOfTypeAndBetweenDates("single", currentDate, currentDate);
        List<Integer> availableDoubleRooms = getAllAvailableRoomIDsOfTypeAndBetweenDates("double", currentDate, currentDate);
        List<Integer> availableFamilyRooms = getAllAvailableRoomIDsOfTypeAndBetweenDates("family", currentDate, currentDate);
        for (Room r : allRooms) {
            String s;
            if (availableSingleRooms.contains(r.getID())
                    || availableDoubleRooms.contains(r.getID())
                    || availableFamilyRooms.contains(r.getID())) {
                s = "Available";
            } else {
                s = "Not Available";
            }

            roomList.add(Integer.toString(r.getID()) + "_" + r.getType() + "_" + s);
        }
        return roomList;
    }

    // Private Constructor (No public constructor exists)
    private Controller() {
        facade = DBFacade.getInstance();
        emailValidator = new EmailValidator();
        facilityMap = new HashMap<>();
    }

    // Makes Controller into a singleton
    public static Controller getInstance() {
        if (instance == null) {
            instance = new Controller();
        }
        return instance;
    }

    /*
     * Create a new reservation in the controller and returns true on success.
     */
    public boolean createNewReservation(String firstName, String familyName,
            String address, String country,
            String phone, String email,
            String agency, Date checkin,
            int nights, int roomID)
            throws WrongNumberOfNights, WrongEmail, UnavailableReservation {
        if (nights <= 0) {
            throw new WrongNumberOfNights("Zero or less than zero"
                    + "number of nights");
        }
        if (!emailValidator.validate(email)) {
            throw new WrongEmail("Email is wrong.");
        }
        Calendar departureDate = Calendar.getInstance();
        departureDate.clear();
        departureDate.setTimeInMillis(checkin.getTime());
        departureDate.add(Calendar.DATE, nights);
        Date departureSQLDate = new Date(departureDate.getTimeInMillis());
        Customer customer = new Customer(-1, address, country, firstName,
                familyName, phone, email, agency);
        List<Customer> customers = new ArrayList();
        customers.add(customer);
        Reservation reservation = new Reservation(-1, roomID,
                -1, checkin, departureSQLDate);
        if (!facade.checkAvailableReservation(reservation)) {
            throw new UnavailableReservation("Another reservation conflict with this reservation");
        }
        return facade.saveReservationInformation(reservation, customers);
    }

    /*
     *   Returns a RoomID of a  random available room during your specified time period
     */
    public List<Integer> getAvailableRoomIDsOfTypeBetweenDates(
            String type, Date arrivalDate, Date departureDate) {

        List<Integer> availableRoomIDs = getAllAvailableRoomIDsOfTypeAndBetweenDates(
                type, arrivalDate, departureDate);
        if (availableRoomIDs.isEmpty()) {
            return null;
        }
        return availableRoomIDs;
    }

    /*
     *   Returns a list of all available Rooms IDs that are withing 2 specified dates 
     *   and are of a certain type
     */
    private List<Integer> getAllAvailableRoomIDsOfTypeAndBetweenDates(
            String type, Date arrivalDate, Date departureDate) {

        // Builds a Map containing all rooms and the boolean true
        // representing available rooms
        Map<Integer, Boolean> roomAvailability = new HashMap<>();
        List<Room> rooms = facade.getAllRooms();
        for (Room room : rooms) {
            if (room.getType().equals(type)) {
                roomAvailability.put(room.getID(), Boolean.TRUE);
            }

        }

        List<Reservation> reservations = facade.getAllReservationsOfSpecificType(type);
        Date bookedArrivalDate;
        Date bookedDepartureDate;

        // Goes through all reservations trying to find conflicting booking 
        // if a conflicting booking is found the roomIDs linked boolean gets 
        // set to false representing it being a not available room
        // becuase how maps work no room exists twice
        for (Reservation r : reservations) {
            bookedArrivalDate = r.getCheckinDate();
            bookedDepartureDate = r.getDepartureDate();
            boolean status = true;

            if (roomAvailability.get(r.getRoomID()) == Boolean.FALSE) {
                status = false;

            }
            if (status) {
                if (arrivalDate.before(bookedArrivalDate)
                        && departureDate.compareTo(bookedArrivalDate) <= 0) {
                    roomAvailability.put(r.getRoomID(), Boolean.TRUE);
                } else if (arrivalDate.compareTo(bookedDepartureDate) >= 0) {
                    roomAvailability.put(r.getRoomID(), Boolean.TRUE);
                } else {
                    roomAvailability.put(r.getRoomID(), Boolean.FALSE);
                }
            }

        }

        // Goes through the map and picks out the available rooms
        List<Integer> availableRooms = new ArrayList<>();
        for (Map.Entry<Integer, Boolean> entry : roomAvailability.entrySet()) {
            if (entry.getValue()) {
                availableRooms.add(entry.getKey());
            }
        }
        return availableRooms;
    }

    /*
     *   Returns a list of a customers currently in the hotel
     */
    public Map<Customer, Integer> getAllCurrentGuests() {
        List<Customer> allCustomers = facade.getAllCustomers();
        Map<Integer, Customer> customerMap = new HashMap<>();
        for (Customer c : allCustomers) {
            customerMap.put(c.getID(), c);
        }
        List<Reservation> allReservations = facade.getAllReservations();
        Calendar rightNow = Calendar.getInstance();
        Date today = new Date(rightNow.getTimeInMillis());
        today = Date.valueOf(today.toString());
        Date arrivalDate;
        Date departureDate;

        Map<Customer, Integer> currentCustomerStatus = new HashMap<>();
        for (Reservation r : allReservations) {
            arrivalDate = r.getCheckinDate();
            departureDate = r.getDepartureDate();
            System.out.println("");
            System.out.println("arrival =" + arrivalDate);
            System.out.println("departure = " + departureDate);
            if (today.compareTo(arrivalDate) == 0) {
                currentCustomerStatus.put(customerMap.get(r.getCustomerID()), 2);
            } else if (today.compareTo(departureDate) == 0) {
                currentCustomerStatus.put(customerMap.get(r.getCustomerID()), 3);
            } else if (today.after(arrivalDate) && today.before(departureDate)) {
                currentCustomerStatus.put(customerMap.get(r.getCustomerID()), 1);
            }

        }
        return currentCustomerStatus;
    }

    /*
     *  Returns a list of strings containing all sport facility names
     *  currently in the database
     */
    public List<String> getAllFacilityNames() {
        List<Facility> facilities = facade.getAllFacilities();
        List<String> facilityNames = new ArrayList<>();
        for (Facility f : facilities) {
            facilityNames.add(f.getName());
            facilityMap.put(f.getName(), f);
        }

        return facilityNames;
    }

    /*
     *   Returns a facility object of the correct name from our HashMap
     */
    public Facility getFacility(String name) {
        Facility f = null;
        if (facilityMap.containsKey(name)) {
            f = facilityMap.get(name);
        }
        return f;
    }

    /*
     *   Returns true if the date and timeslot of the specified facility is 
     * available
     */
    public boolean checkAvailableFacilityBooking(FacilityBooking fb) {
        List<FacilityBooking> bookings
                = facade.getAllBookingsOfSpecificDateTimeslotFacility(
                        fb.getBookingDate(), fb.getTimeslot(), fb.getFacilityID());
        List<Facility> facility = facade.getFacilityByID(fb.getFacilityID());
        return bookings.size() < facility.get(0).getCapacity();
    }

    public boolean saveFacilityBooking(FacilityBooking fb) {
        return facade.saveFacilityBooking(fb);
    }

    public List<FacilityBooking> getAllFacilityBookingOfSpecificUser(int ID) {
        return facade.getAllFacilityBookingOfSpecificUser(ID);
    }

    public boolean removeFacilityBooking(int ID) {
        return facade.removeFacilityBooking(ID);
    }

    public List<FacilityBooking> getAllFacilityBookingsOfSpecificDate(Date date) {
        return facade.getAllFacilityBookingsOfSpecificDate(date);
    }

    public List<FacilityBooking> getAllFacilityBookingsOfSpecificDateAndUser(Date date, int id) {
        return facade.getAllFacilityBookingsOfSpecificDateAndUser(date, id);
    }

    public List<FacilityBooking> getAllBookingsOfSpecificDateTimeslotFacility(
            Date date, int timeslot, Facility facility) {
        return facade.getAllBookingsOfSpecificDateTimeslotFacility(date, timeslot, facility.getID());
    }

    /*
     * Returns true if specified user already have a facility booking on
     * given date and timeslot
     */
    public boolean doesUserHaveFacilityBookingOnSpecificDateAndTimeslot(
            Date date, int userID, int timeslot) {
        List<FacilityBooking> bookings = facade.getAllFacilityBookingsOfSpecificDateTimeslotUser(
                date, userID, timeslot);
        return !bookings.isEmpty();
    }

    /*
     * Get specific user by id.
     */
    public HotelUser getUserForSpecificUserID(int userID) {
        return facade.getUser(userID);
    }

    public List<QueueEntry> getQueueEntriesOfSpecificBooking(int id) {
        return facade.getQueueEntriesOfSpecificBooking(id);
    }

    public int getCurrentUserID() {
        return 1;
    }

    public String getFacilityName(int facilityID) {
        for (Map.Entry<String, Facility> entry : facilityMap.entrySet()) {
            String string = entry.getKey();
            Facility facility = entry.getValue();
            if (facility.getID() == facilityID) {
                return string;
            }
        }
        return "";
    }

    public List<Reservation> getAllReservations() {
        return facade.getAllReservations();
    }

    public List<Integer> getAllUserIDs() {
        List<Integer> userIDs = new ArrayList<>();
        List<HotelUser> users = facade.getAllUsers();
        for (HotelUser u : users) {
            userIDs.add(u.getId());
        }
        return userIDs;
    }

    public List<Reservation> searchReservation(Object variable, String columnName) {
        return facade.searchReservation(variable, columnName);
    }

    public List<Room> searchRoom(Object variable, String columnName) {
        return facade.searchRoom(variable, columnName);
    }

    public List<HotelUser> searchHotelUser(Object variable, String columnName) {
        return facade.searchHotelUser(variable, columnName);
    }

    public List<Customer> searchCustomer(Object variable, String columnName) {
        return facade.searchCustomer(variable, columnName);
    }

    public List<Facility> searchFacility(Object variable, String columnName) {
        return facade.searchFacility(variable, columnName);
    }

    public List<FacilityBooking> searchFacilityBooking(Object variable, String columnName) {
        return facade.searchFacilityBooking(variable, columnName);
    }
    
    public boolean queueUserForSpecificTimeslot(int bookingID, int userID){
        QueueEntry entry = new QueueEntry(-1, userID, bookingID);
        return facade.saveQueueEntry(entry);
    }
    
    public void shutdownConnection(){
        facade.shutdown();
    }
    
    public boolean updateFacilityBookingUserID(int bookingID, int userID) {
        return facade.updateFacilityBookingUserID(bookingID, userID);
    }
    
    /*
     * Pop user from the queue for specific booking id and return his id.
     */
    public int popUserFromQueueForID(int bookingID) {
        List<QueueEntry> entries = this.getQueueEntriesOfSpecificBooking(bookingID);
        facade.deleteQueueEntryForSpecificID(bookingID, entries.get(0).getUserID());
        return entries.get(0).getUserID();
    }

    public List<Integer> getAllUnpaidReservationIDs() {
        List<UnpaidReservation> unpaidReservationList = facade.getAllUnpaidReservationIDs();
        List<Integer> IDlist = new ArrayList();
        for (UnpaidReservation r : unpaidReservationList) {
            IDlist.add(r.getID());
        }
        return IDlist;
    }

    public boolean removeUnpaidReservation(int ID) {
        return facade.removeUnpaidReservation(ID);
    }

    //removes reservation and all the entrys that have reservation_id as a foreign key
    public boolean removeReservation(int ID) {
        return facade.removeReservation(ID);
    }

    public Date getUnpaidReservationBookingDateByID(int ID) {
        return facade.getUnpaidReservationBookingDateByID(ID);
    }

    public List<InstructorBooking> getInstructorBookings(int userID) {
        return facade.getInstructorBookings(userID);
    }
    
    public List<InstructorBooking> getInstructorBookingByUserIDAndDate(
            int userID, Date date) {
        return facade.getInstructorBookingByUserIDAndDate(userID, date);
    }

    void saveReservationWithGuests(Reservation reservation, List<Customer> guests) {
        facade.saveReservationInformation(reservation, guests);
    }

}
