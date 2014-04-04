package dataSource;

import domain.FacilityBooking;
import domain.Reservation;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class ReservationMapper extends AbstractMapper implements ReservationMapperInterface {

    public ReservationMapper(Connection con) {
        super(con);
    }

//    This method returns a reservation object based on the ID parameter.
//    It uses a select sql query from the reservation table.
    @Override
    public Reservation getReservation(int ID) {
        List<Reservation> reservation = search(ID, "id");
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
        ArrayList<Reservation> seq = executeQueryAndGatherResults(
                Reservation.class,
                "SELECT reservationSeq.nextval "
                + "FROM dual",
                "Fail in ReservationMapper - saveReservation",
                new String[]{"ID"}, new int[]{DataType.INT});
        r.setID(seq.get(0).getID());
        int result = executeSQLInsert(
                "INSERT INTO reservation VALUES (?, ?, ?, ?, ?)",
                "Fail in ReservationMapper - saveReservation",
                r.getID(), r.getRoomID(), r.getCustomerID(),
                r.getCheckinDate(), r.getDepartureDate());
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
                new String[]{"ID", "roomID", "customerID", "checkinDate", "departureDate"},
                new int[]{DataType.INT, DataType.INT, DataType.INT, DataType.DATE, DataType.DATE},
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
                "Fail in RoomMapper - getRoom",
                new String[]{"ID", "roomID", "customerID", "checkinDate", "departureDate"},
                new int[]{DataType.INT, DataType.INT, DataType.INT, DataType.DATE, DataType.DATE});
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
                "Fail in RoomMapper - getRoom",
                new String[]{"ID", "roomID", "customerID", "checkinDate", "departureDate"},
                new int[]{DataType.INT, DataType.INT, DataType.INT, DataType.DATE, DataType.DATE},
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
        List<Reservation> reservation = executeQueryAndGatherResults(
                Reservation.class,
                "select * "
                + "from reservation "
                + "where " + columnName + " = ?",
                "Fail in ReservationMapper - search",
                new String[]{"ID", "roomID", "customerID", "checkinDate", "departureDate"},
                new int[]{DataType.INT, DataType.INT, DataType.INT, DataType.DATE, DataType.DATE},
                variable);
        return reservation;
    }

}
