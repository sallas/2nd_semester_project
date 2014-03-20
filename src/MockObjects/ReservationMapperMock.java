package MockObjects;

import dataSource.ReservationMapperInterface;
import domain.Reservation;
import java.util.HashMap;
import java.util.Map;

public class ReservationMapperMock implements ReservationMapperInterface {

    Map<Integer, Reservation> reservations;
    
    public void setReservations(Map<Integer, Reservation> reservations) {
        this.reservations = reservations;
    }

    public ReservationMapperMock() {
        reservations = new HashMap<>();
    }
    
    @Override
    public Reservation getReservation(int ID)
    {
        return reservations.get(ID);
    }
    
    
    @Override
    public boolean saveReservation(Reservation r)
    {
        reservations.put(r.getID(), r);
        return true;
    }
    

}
