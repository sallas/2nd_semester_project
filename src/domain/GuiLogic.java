package domain;

import java.sql.Date;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.Map;
import javax.swing.DefaultListModel;
import javax.swing.table.DefaultTableModel;
import utility.DateLogic;

public class GuiLogic {

    private static GuiLogic instance;
    private Controller control;
    private int currentUserID;
    private List<String> timeslots;
    private FacilityBooking currentFacilityBooking;
    private DateFormat df = new SimpleDateFormat("dd/MM/yyyy");

    private GuiLogic() {
        control = Controller.getInstance();
        currentUserID = 1;
    }

    public static GuiLogic getInstance() {
        if (instance == null) {
            instance = new GuiLogic();
        }
        return instance;
    }

    public String[] setUpDates(List<Date> dates) {
        String[] dateStrings = new String[8];
        Date today = DateLogic.getCurrentTimeInSQLDate();
        for (int i = 0; i < 8; i++) {
            dates.add(today);
            dateStrings[i] = dates.get(i).toString();
            today = DateLogic.addDayToSQLDate(today);
        }
        return dateStrings;
    }

    public void setUpTimeslots() {
        timeslots = new ArrayList<>();
        for (int i = 0; i < 12; i++) {
            timeslots.add((8 + i) + " - " + (9 + i));
        }
    }

    public int getUserID(String ID) {
        int userID;
        try {
            userID = Integer.parseInt(ID);
        } catch (NumberFormatException ex) {
            return -1;
        }
        List<Integer> userIDs = control.getAllUserIDs();
        if (userIDs.contains(userID)) {
            return userID;
        }
        return -1;
    }

    public void fillAvailabilityTable(int facilityID, Date checkDate, DefaultTableModel model) {
        FacilityBooking fb;
        for (int i = 0; i < 12; i++) {
            fb = new FacilityBooking(99, facilityID, checkDate, i + 1, currentUserID);
            if (control.checkAvailableFacilityBooking(fb)) {
                String s = timeslots.get(i);
                model.addRow(new Object[]{s, "Available"});
            } else {
                model.addRow(new Object[]{timeslots.get(i), "Unavailable"});
            }
        }
    }

    public int getCurrentAmountOfBookingsOnSpecificDate(Date date) {
        List<FacilityBooking> bookings
                = control.getAllFacilityBookingsOfSpecificDateAndUser(date, currentUserID);
        return bookings.size();
    }

    public boolean userHasBookingOnDateTimeslot(Date checkDate, int timeslot) {
        return control.doesUserHaveFacilityBookingOnSpecificDateAndTimeslot(
                checkDate, currentUserID, timeslot);
    }

    public boolean saveCurrentFacilityBooking() {
        return control.saveFacilityBooking(currentFacilityBooking);
    }

    public void setCurrentFacilityBooking(int facilityID, Date checkDate, int timeslot) {
        currentFacilityBooking
                = new FacilityBooking(-1, facilityID, checkDate, timeslot, currentUserID);
    }

    public void setCurrentFacilityBooking(int facilityID, Date checkDate, int timeslot, int userID) {
        currentFacilityBooking
                = new FacilityBooking(-1, facilityID, checkDate, timeslot, userID);
    }

    public boolean checkAvailableCurrentFacilityBooking() {
        return control.checkAvailableFacilityBooking(currentFacilityBooking);
    }

    public void setCurrentUserID(int currentUserID) {
        this.currentUserID = currentUserID;
    }

    public void refreshGuestTable(DefaultTableModel model) {
        Map<Customer, Integer> customers = control.getAllCurrentGuests();
        Calendar rightNow = Calendar.getInstance();
        Calendar midday = DateLogic.getCurrentTimeCalendarDate();
        midday.set(Calendar.HOUR_OF_DAY, 12);

        for (Map.Entry<Customer, Integer> entry : customers.entrySet()) {
            if (rightNow.after(midday) && entry.getValue() == 3) {
                continue;
            }
            Object[] ob = new Object[9];
            Customer c = entry.getKey();
            Integer status = entry.getValue();
            ob[0] = c.getID();
            ob[1] = c.getFirst_name();
            ob[2] = c.getLast_name();
            ob[3] = c.getCountry();
            ob[4] = c.getAddres();
            ob[5] = c.getPhone();
            ob[6] = c.getTravel_agency();
            ob[7] = c.getEmail();
            if (status == 1) {
                ob[8] = "Checked in";
            } else if (status == 2) {
                ob[8] = "Arriving today";
            } else {
                ob[8] = "Leaving today";
            }
            model.addRow(ob);
        }
    }

    public void refreshRoomInfoTable(DefaultTableModel model) {
        List<String> roomList = control.getRooms();
        Object[] ob = new Object[3];
        for (String s : roomList) {
            String[] separated = s.split("_");
            ob[0] = separated[0];
            ob[1] = separated[1];
            ob[2] = separated[2];
            model.addRow(ob);
        }
    }

    public void initOwnBookingTable(DefaultTableModel model, List<FacilityBooking> listOfBookings) {
        listOfBookings = control.getAllFacilityBookingOfSpecificUser(currentUserID);
        control.getAllFacilityNames();
        for (int i = model.getRowCount() - 1; i >= 0; i--) {
            model.removeRow(i);
        }
        for (FacilityBooking i : listOfBookings) {
            model.addRow(new Object[]{control.getFacilityName(i.getFacilityID()),
                df.format(i.getBookingDate()), (i.getTimeslot() + 7) + " - " + (i.getTimeslot() + 8)});
        }
    }

    public void refreshReservationTable(DefaultTableModel model) {
        List<domain.Reservation> reservations = control.getAllReservations();
        for (domain.Reservation r : reservations) {
            Object[] ob = new Object[5];
            ob[0] = r.getID();
            ob[1] = r.getRoomID();
            ob[2] = r.getCustomerID();
            ob[3] = r.getCheckinDate();
            ob[4] = r.getDepartureDate();
            model.addRow(ob);
        }
    }

    public DefaultListModel search(String currentObject, String currentVariable, Object variable, DefaultListModel model) {
        model = new DefaultListModel();
        List<String> results = new ArrayList<>();
        if ("reservation".equalsIgnoreCase(currentObject)) {
            List<Reservation> reservations = control.searchReservation(variable, currentVariable);
            results = fill(reservations);
        } else if("room".equalsIgnoreCase(currentObject)) {
            List<Room> rooms = control.searchRoom(variable, currentVariable);
            results = fill(rooms);
        }
        
        
        for (String s : results) {
            model.addElement(s);
        }
        return model;
    }

    private <T> List<String> fill(List<T> objects) {
        List<String> results = new ArrayList<>();
        for (Object o : objects) {
            results.add(o.toString());
        }
        return results;
    }
}
