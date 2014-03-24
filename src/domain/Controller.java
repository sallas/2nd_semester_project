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
    private Reservation currentReservation;
    private static Controller instance = null;
    private int currentRoomID;

    /*
     * Init a controller.
     */
    private Controller() {
        facade = DBFacade.getInstance();
        currentReservation = new Reservation(0, 0, 0, null, 0);
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
    public boolean createNewReservation() {
        //return facade.saveReservatoin();
        return true;
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
