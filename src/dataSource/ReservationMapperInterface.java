package dataSource;

import domain.Reservation;
import java.util.List;

public interface ReservationMapperInterface {

    Reservation getReservation(int ID);

    boolean saveReservation(Reservation r);
    
    List<Reservation> getAllReservationsOfSpecificType(String type);
    
    List<Reservation> getAllReservations();
}
