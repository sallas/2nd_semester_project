package domain;

import dataSource.DBFacade;
import java.sql.Date;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;

public class Controller {

    private DBFacade facade;
    private EmailValidator emailValidator;
    private static Controller instance = null;
    private int currentRoomID;
    private Map<String, Facility> facilityMap;

    public List<String> getRooms() {
        List<String> roomList = new ArrayList();
        List<Room> allRooms = facade.getAllRooms();
        java.util.Date utilDate = new java.util.Date();
        java.sql.Date currentDate = new java.sql.Date(utilDate.getTime());
        List<Integer> availableSingleRooms
                = getAllAvailableRoomIDsOfTypeAndBetweenDates("single", currentDate, currentDate);
        List<Integer> availableDoubleRooms
                = getAllAvailableRoomIDsOfTypeAndBetweenDates("double", currentDate, currentDate);
        List<Integer> availableFamilyRooms
                = getAllAvailableRoomIDsOfTypeAndBetweenDates("family", currentDate, currentDate);
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

    private Controller() {
        facade = DBFacade.getInstance();
        emailValidator = new EmailValidator();
        facilityMap = new HashMap<>();
    }

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
        Reservation reservation = new Reservation(-1, roomID,
                -1, checkin, departureSQLDate);
        if (!facade.checkAvailableReservation(reservation)) {
            throw new UnavailableReservation("Another reservation conflict with this reservation");
        }
        return facade.saveReservationInformation(reservation, customer);
    }

    /*
     *   Returns a availableRooms of a avialable room during your specified time period
     */
    public int getAvailableRoomIDOfTypeBetweenDates(String type, Date arrivalDate, Date departureDate) {

        List<Integer> availableRoomIDs
                = getAllAvailableRoomIDsOfTypeAndBetweenDates(type, arrivalDate, departureDate);
        if (availableRoomIDs.isEmpty()) {
            currentRoomID = -1;
            return -1;
        }
        Random rand = new Random();
        currentRoomID = availableRoomIDs.get(rand.nextInt(availableRoomIDs.size()));
        return currentRoomID;
    }

    private List<Integer> getAllAvailableRoomIDsOfTypeAndBetweenDates(String type, Date arrivalDate, Date departureDate) {
        Date bookedArrivalDate;
        Date bookedDepartureDate;

        List<Reservation> reservations
                = facade.getAllReservationsOfSpecificType(type);

        Map<Integer, Boolean> roomAvailability = new HashMap<>();
        List<Room> rooms = facade.getAllRooms();
        for (Room room : rooms) {
            if (room.getType().equals(type)) {
                roomAvailability.put(room.getID(), Boolean.TRUE);
            }

        }

        for (Reservation r : reservations) {
            bookedArrivalDate = r.getCheckinDate();
            bookedDepartureDate = r.getDepartureDate();
            boolean status = true;

            if (roomAvailability.get(r.getRoomID()) == Boolean.FALSE) {
                status = false;

            }
            if (status) {
                if (arrivalDate.before(bookedArrivalDate)
                        && departureDate.before(bookedArrivalDate)) {
                    roomAvailability.put(r.getRoomID(), Boolean.TRUE);
                } else if (arrivalDate.after(bookedDepartureDate)) {
                    roomAvailability.put(r.getRoomID(), Boolean.TRUE);
                } else {
                    roomAvailability.put(r.getRoomID(), Boolean.FALSE);
                }
            }

        }
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
    public List<Customer> getAllCurrentGuests() {
        List<Customer> allCustomers = facade.getAllCustomers();
        Map<Integer, Customer> customerMap = new HashMap<>();
        for (Customer c : allCustomers) {
            customerMap.put(c.getID(), c);
        }
        List<Reservation> allReservations = facade.getAllReservations();
        List<Customer> currentGuests = new ArrayList<>();
        Date rightNow = new Date(Calendar.getInstance().getTimeInMillis());
        Date arrivalDate;
        Date departureDate;

        for (Reservation r : allReservations) {
            arrivalDate = r.getCheckinDate();
            departureDate = r.getDepartureDate();
            if (rightNow.after(arrivalDate) && rightNow.before(departureDate)) {
                currentGuests.add(customerMap.get(r.getCustomerID()));
            }

        }
        return currentGuests;
    }

    /*
     *   Returns a list of strings containing all sport facility names
     * currently in the database
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
        if(facilityMap.containsKey(name))
            f = facilityMap.get(name);
        return f;
    }
    
    public boolean checkAvailableFacilityBooking(FacilityBooking fb) {
        return facade.checkAvailableFacilityBooking(fb);
    }

}
