package MockObjects;

import dataSource.ReservationMapperInterface;
import domain.Reservation;
import domain.UnpaidReservation;
import java.sql.Date;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ReservationMapperMock implements ReservationMapperInterface {

    private Map<Integer, Reservation> reservations;
    private List<Integer> roomID;
    private int sequenceNo;

    public void setReservations(Map<Integer, Reservation> reservations) {
        this.reservations = reservations;
    }

    public void setRoomID(List<Integer> roomID) {
        this.roomID = roomID;
    }

    public void setSequenceNo(int sequenceNo) {
        this.sequenceNo = sequenceNo;
    }

    public ReservationMapperMock() {
        reservations = new HashMap<>();
        roomID = new ArrayList<>();
    }

    /*
     * Returns the reservation with key ID if it exists
     * otherwise it will return null
     */
    @Override
    public Reservation getReservation(int ID) {
        Reservation r = null;
        if (reservations.containsKey(ID)) {
            r = reservations.get(ID);
        }
        return r;
    }

    @Override
    public boolean saveReservation(Reservation r) {
        if (!roomID.contains(r.getRoomID())) {
            return false;
        }
        r.setID(sequenceNo);
        reservations.put(sequenceNo, r);
        sequenceNo++;
        return true;
    }

    @Override
    public List<Reservation> getAllReservationsOfSpecificType(String type) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public List<Reservation> getAllReservations() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public boolean checkAvailableReservation(Reservation r) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void lockReservationTable() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public List<UnpaidReservation> getAllUnpaidReservationIDs() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public boolean removeUnpaidReservation(int ID) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public boolean removeReservation(int ID) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public Date getUnpaidReservationBookingDateByID(int ID) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public List<Reservation> search(Object variable, String columnName) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

}
