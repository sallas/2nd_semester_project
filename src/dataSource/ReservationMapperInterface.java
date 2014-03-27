package dataSource;

import domain.Reservation;
import java.util.List;

public interface ReservationMapperInterface {

    /*
     * This method returns a reservation object based on the ID parameter.
     */
    Reservation getReservation(int ID);

    /*
     * This method saves a reservation into the database.
     * Returns false if reservation wasn't able to be entered
     */
    boolean saveReservation(Reservation r);

    /*
     * This method returns a list of all the reservation objects of specified type
     */
    List<Reservation> getAllReservationsOfSpecificType(String type);

    /*
     *   Returns a list of all reservations
     */
    List<Reservation> getAllReservations();

    /*
     * Returns true if no reservation conflicts with the reservation r
     * Otherwise returns false
     */
    boolean checkAvailableReservation(Reservation r);
}
