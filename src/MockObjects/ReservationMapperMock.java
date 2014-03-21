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
    
    /*
    * Returns the reservation with key ID if it exists
    * otherwise it will return null
    */
    @Override
    public Reservation getReservation(int ID)
    {
        Reservation r = null;
        if(reservations.containsKey(ID))
            r = reservations.get(ID);
        return r;
    }
    
    
    @Override
    public boolean saveReservation(Reservation r)
    {
        reservations.put(r.getID(), r);
        return true;
    }
    

}
