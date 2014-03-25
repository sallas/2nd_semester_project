package domain;

import dataSource.DBFacade;
import java.sql.Date;
import java.text.Format;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
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

    public List<String> getRooms() {
        List<String> roomList = new ArrayList();
        List<Room> tempRoomList = facade.getAllRooms();
        java.util.Date utilDate = new java.util.Date();
        java.sql.Date currentDate = new java.sql.Date(utilDate.getTime());
        for (Room r : tempRoomList) {
            Date date = facade.getRoomAvailabilityDate(r.getID());
            Format formatter = new SimpleDateFormat("yyyy-MM-dd");
            String s = formatter.format(date);
            if (date.compareTo(currentDate) < 0) {
                s = "NOW";
            }
            roomList.add(Integer.toString(r.getID()) + "_" + r.getType() + "_" + s);
        }
        return roomList;
    }
    private Controller() {
        facade = DBFacade.getInstance();
        emailValidator = new EmailValidator();
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
            throws WrongNumberOfNights, WrongEmail {
        if (nights <= 0){
            throw new WrongNumberOfNights("Zero or less than zero"
                      + "number of nights");
        }
        if (!emailValidator.validate(email)){
            throw new WrongEmail("Email is wrong.");
        }
        Customer customer = new Customer(-1 , address, country, firstName,
                                         familyName, phone, email, agency);
        Reservation reservation = new Reservation(-1, roomID,
                                         -1, checkin, nights);
        return facade.saveReservationInformation(reservation, customer);
    }

    public int getAvailableRoomOfSpecificType(String type,
            Calendar arrivalDate, Calendar departureDate) {

        Calendar bookedArrivalDate = Calendar.getInstance();
        bookedArrivalDate.clear();
        Calendar bookedDepartureDate;

        List<Reservation> reservations
                = facade.getAllReservationsOfSpecificType(type);

        Map<Integer, Boolean> roomAvailability = new HashMap<>();
        for (Reservation r : reservations) {
            bookedArrivalDate.setTimeInMillis(r.getCheckinDate().getTime());
            bookedDepartureDate = (Calendar) bookedArrivalDate.clone();
            bookedDepartureDate.add(Calendar.DATE, r.getNumberNights());
            boolean status = true;
            if (roomAvailability.containsKey(r.getRoomID())) {
                if (roomAvailability.get(r.getRoomID()) == Boolean.FALSE) {
                    status = false;
                }
            }
            if (status) {
                if (arrivalDate.before(bookedArrivalDate)) {
                    if (departureDate.before(bookedArrivalDate)) {
                        roomAvailability.put(r.getRoomID(), Boolean.TRUE);
                    }
                }
                else if (arrivalDate.after(bookedDepartureDate)) {
                    roomAvailability.put(r.getRoomID(), Boolean.TRUE);
                }
                else {
                    roomAvailability.put(r.getRoomID(), Boolean.FALSE);
                }
            }

        }
        List<Integer> roomID = new ArrayList<>();

        for (Map.Entry<Integer, Boolean> entry : roomAvailability.entrySet()) {
            if (entry.getValue()) {
                roomID.add(entry.getKey());
            }
        }
        if (roomID.isEmpty()) {
            currentRoomID = -1;
            return -1;
        }
        Random rand = new Random();
        currentRoomID = roomID.get(rand.nextInt(roomID.size()));
        return currentRoomID;
    }

}
