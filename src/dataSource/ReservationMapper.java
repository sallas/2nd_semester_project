package dataSource;

import domain.FacilityBooking;
import domain.Reservation;
import domain.UnpaidReservation;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import utility.DateLogic;

public class ReservationMapper extends AbstractMapper implements ReservationMapperInterface {

    public ReservationMapper(Connection con) {
        super(con, "reservation", Reservation.class);
    }

//    This method returns a reservation object based on the ID parameter.
//    It uses a select sql query from the reservation table.
    @Override
    public Reservation getReservation(int ID) {
        List<Reservation> reservation = generalSearch("id",
                "Fail in ReservationMapper - getReservation", ID);
        if (reservation.isEmpty()) {
            return null;
        } else {
            return reservation.get(0);
        }
    }

    //This method saves a reservation into the database.
    //It gets the reservation object as a parameter.
    //The first SQL query is meant to create a unique ID for the reservation.
    //The second query simply inserts the data into the database table.
    @Override
    public boolean saveReservation(Reservation r) {
        int seqNum = getSequenceNumber("SELECT reservationSeq.nextval "
                + "FROM dual",
                "Fail in ReservationMapper - saveReservation");
        r.setID(seqNum);
        int result = executeSQLInsert(
                "INSERT INTO reservation VALUES (?, ?, ?, ?, ?)",
                "Fail in ReservationMapper - saveReservation",
                r.getID(), r.getRoomID(), r.getCustomerID(),
                r.getCheckinDate(), r.getDepartureDate());
        if (result == 1) {
            executeSQLInsert(
                    "INSERT INTO unpaid_reservations VALUES (?, ?)",
                    "Fail in ReservationMapper - saveReservation (unpaid_reservations table)",
                    seqNum, DateLogic.getCurrentTimeInSQLDate());
        }
        return result == 1;
    }

    //This method returns a list of all the reservation objects of specified type
    @Override
    public List<Reservation> getAllReservationsOfSpecificType(String type) {

        ArrayList<Reservation> reservation = executeQueryAndGatherResults(
                Reservation.class,
                "select * "
                + "from reservation "
                + "where room_id in (select id from room where type = ?)",
                "Fail in ReservationMapper - getReservation",
                type);
        return reservation;
    }

    /*
     *   Returns a list of all reservations
     */
    @Override
    public List<Reservation> getAllReservations() {

        ArrayList<Reservation> reservation = executeQueryAndGatherResults(
                Reservation.class,
                "SELECT * FROM reservation",
                "Fail in RoomMapper - getRoom");
        if (reservation.isEmpty()) {
            return null;
        } else {
            return reservation;
        }
    }

    /*
     * Returns true if no reservation conflicts with the reservation r
     * Otherwise returns false
     */
    @Override
    public boolean checkAvailableReservation(Reservation r) {

        boolean available;
        ArrayList<Reservation> reservation = executeQueryAndGatherResults(
                Reservation.class,
                "SELECT * FROM reservation "
                + "WHERE room_id = ? AND "
                + "(((? >= checkin_date AND "
                + "? < departure_date) OR "
                + "(? > checkin_date AND "
                + "? <= departure_date)) OR "
                + "(checkin_date  < ? AND "
                + "checkin_date >= ?))",
                "Fail in ReservationMapper - checkAvailableReservation",
                r.getRoomID(), r.getCheckinDate(), r.getCheckinDate(), r.getDepartureDate(),
                r.getDepartureDate(), r.getDepartureDate(), r.getCheckinDate());
        available = reservation.isEmpty();
        return available;
    }

    /*
     * Locks the table reservation so no one else will be able to 
     * write to it until we commit something
     * Used to make sure we don't double book
     */
    @Override
    public void lockReservationTable() {
        executeSQLQuery(
                "LOCK TABLE reservation in exclusive mode",
                "Fail in ReservationMapper - lockReservationTable"
        );
    }

    @Override
    public List<Reservation> search(Object variable, String columnName) {
        return generalSearch(columnName,
                "Fail in ReservationMapper - search ", variable);
    }

    @Override
    public List<UnpaidReservation> getAllUnpaidReservationIDs() {
        return executeQueryAndGatherResults(
                UnpaidReservation.class,
                "SELECT * FROM unpaid_reservations",
                "Fail in ReservationMapper - getAllUnpaidReservationIDs"
        );
    }

    @Override
    public boolean removeUnpaidReservation(int ID) {
        return 1 == executeSQLInsert(
                "DELETE FROM unpaid_reservations WHERE ID = ?",
                "Fail in ReservationMapper - removeUnpaidReservation",
                ID);
    }

    @Override
    public boolean removeReservation(int ID) {
        return 1 == executeSQLInsert(
                "DELETE FROM reservation WHERE ID = ?",
                "Fail in ReservationMapper - removeReservation",
                ID);
    }

    @Override
    public Date getUnpaidReservationBookingDateByID(int ID) {
        return generalSearch(UnpaidReservation.class, "unpaid_reservations",
                "ID", "Fail in ReservationMapper -"
                + " getUnpaidReservationBookingDateByID",
                ID).get(0).getBookingDate();
    }
}
