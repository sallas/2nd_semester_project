package dataSource;

import domain.Reservation;

public interface ReservationMapperInterface {

    Reservation getReservation(int ID);

    boolean saveReservation(Reservation r);
}
