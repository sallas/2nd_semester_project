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
    private List<FacilityBooking> listOfBookings;
    private DateFormat df = new SimpleDateFormat("dd/MM/yyyy");
    private List<Customer> guests;
    private EmailValidator emailValidator;

    private GuiLogic() {
        control = Controller.getInstance();
        emailValidator = new EmailValidator();
        currentUserID = 2;
        setUpTimeslots();
        guests = new ArrayList<>();
    }

    public int getCurrentUserID() {
        return currentUserID;
    }

    public FacilityBooking getCurrentFacilityBooking() {
        return currentFacilityBooking;
    }

    public void setCurrentFacilityBooking(FacilityBooking currentFacilityBooking) {
        this.currentFacilityBooking = currentFacilityBooking;
    }

    public static GuiLogic getInstance() {
        if (instance == null) {
            instance = new GuiLogic();
        }
        return instance;
    }

    /*
     * Returns a array of date Strings while also builing up the dates List
     */
    public String[] setUpDates(List<Date> dates) {
        String[] dateStrings = new String[8];
        Date today = DateLogic.getCurrentDateInSQLDate();
        for (int i = 0; i < 8; i++) {
            dates.add(today);
            dateStrings[i] = dates.get(i).toString();
            today = DateLogic.addDayToSQLDate(today);
        }
        return dateStrings;
    }

    private void setUpTimeslots() {
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
            fb = new FacilityBooking(99, facilityID, checkDate, i + 1, currentUserID, true);
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
                = new FacilityBooking(-1, facilityID, checkDate, timeslot, currentUserID, true);
    }

    public void setCurrentFacilityBooking(int facilityID, Date checkDate, int timeslot, int userID) {
        currentFacilityBooking
                = new FacilityBooking(-1, facilityID, checkDate, timeslot, userID, true);
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
        Calendar midday = DateLogic.getCurrentDateCalendarDate();
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

    public void initOwnBookingTable(DefaultTableModel model) {
        this.listOfBookings = control.getAllFacilityBookingOfSpecificUser(currentUserID);
        control.getAllFacilityNames();
        for (int i = model.getRowCount() - 1; i >= 0; i--) {
            model.removeRow(i);
        }
        for (FacilityBooking i : this.listOfBookings) {
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

    public List<FacilityBooking> getListOfBookings() {
        return listOfBookings;
    }

    public DefaultListModel search(String currentObject, String currentVariable, Object variable, DefaultListModel model) {
        model = new DefaultListModel();
        List<String> results = new ArrayList<>();
        if ("reservation".equalsIgnoreCase(currentObject)) {
            List<Reservation> reservations = control.searchReservation(variable, currentVariable);
            results = fill(reservations);
        } else if ("room".equalsIgnoreCase(currentObject)) {
            List<Room> rooms = control.searchRoom(variable, currentVariable);
            results = fill(rooms);
        } else if ("user".equalsIgnoreCase(currentObject)) {
            List<HotelUser> rooms = control.searchHotelUser(variable, currentVariable);
            results = fill(rooms);
        } else if ("facility".equalsIgnoreCase(currentObject)) {
            List<Facility> rooms = control.searchFacility(variable, currentVariable);
            results = fill(rooms);
        } else if ("sports booking".equalsIgnoreCase(currentObject)) {
            List<FacilityBooking> rooms = control.searchFacilityBooking(variable, currentVariable);
            results = fill(rooms);
        } else if ("customer".equalsIgnoreCase(currentObject)) {
            List<Customer> rooms = control.searchCustomer(variable, currentVariable);
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

    public void fillMyInstructorBookingTable(DefaultTableModel model) {
        List<InstructorBooking> bookings = control.getInstructorBookings(currentUserID);
        for (InstructorBooking ib : bookings) {
            if (ib.getBookedDate().before(DateLogic.getCurrentDateInSQLDate())) {
                continue;
            }
            Object[] ob = new Object[3];
            ob[0] = ib.getFacilityID();
            ob[1] = ib.getBookedDate();
            ob[2] = timeslots.get(ib.getTimeslot() - 1);
            model.addRow(ob);
        }
    }
    
    public void initNortificationsList(DefaultListModel<Object> model) {
        model.clear();
        List<Nortification> nor = control.getAllNortifications();
        for(Nortification el: nor){
            model.addElement(el.getMessage());
        }
    }

    public void fillMyInstructorBookingTableForToday(DefaultTableModel model) {
        List<InstructorBooking> bookings
                = control.getInstructorBookingByUserIDAndDate(
                        currentUserID, DateLogic.getCurrentDateInSQLDate());
        for (int i = 0; i < 12; i++) {
            Object[] ob = new Object[3];
            ob[0] = timeslots.get(i);
            InstructorBooking booking = null;
            for (InstructorBooking ib : bookings) {
                if (ib.getTimeslot() == i + 1) {
                    booking = ib;
                }
            }
            if (booking != null) {
                ob[1] = "Booked";
                ob[2] = booking.getFacilityID();
            } else {
                ob[1] = "Not Booked";
                ob[2] = "None";
            }
            model.addRow(ob);
        }
    }

    public void addGuestToCurrentReservation(String firstName,
            String familyName, String address, String country,
            String phone, String travelAgency, String email) throws WrongEmail {
        if (!emailValidator.validate(email)) {
            throw new WrongEmail("Email is wrong.");
        }
        Customer c = new Customer(-1, address, country, firstName,
                familyName, phone, email, travelAgency);

        guests.add(c);
    }

    public boolean saveReservation(Date arrival, int nights,
            int roomID) {
        Calendar departureDate = Calendar.getInstance();
        departureDate.clear();
        departureDate.setTimeInMillis(arrival.getTime());
        departureDate.add(Calendar.DATE, nights);
        Date departureSQLDate = new Date(departureDate.getTimeInMillis());
        Reservation reservation = new Reservation(-1, roomID,
                -1, arrival, departureSQLDate);
        return control.saveReservationWithGuests(reservation, guests);
    }

    public List<String> getCurrentReservationCustomerInfo() {
        List<String> customerInfo = new ArrayList<>();
        for (Customer c : guests) {
            String s = "";
            s += c.getFirst_name() + " ";
            s += c.getLast_name() + " ";
            s += c.getEmail();
            customerInfo.add(s);
        }
        return customerInfo;
    }

    public void resetGuests() {
        guests = new ArrayList<>();
    }

    public String checkCredentials(String text, String password) {
        HotelUser user = control.checkCredentials(text, password);
        if(user == null) {
            return null;
        }
        currentUserID = user.getId();
        return user.getStatus();
    }
}
