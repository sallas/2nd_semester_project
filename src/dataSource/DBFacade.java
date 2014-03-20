package dataSource;

import domain.Reservation;

public class DBFacade {

    private static ReservationMapperInterface rm;
    private static DBFacade instance;

    private DBFacade() {
        rm = new ReservationMapper(DBConnector.getConnection());
    }

    public static DBFacade getInstance() {
        return instance;
    }

    public Reservation getReservation(int ID) {
        return rm.getReservation(ID);
    }

    public boolean saveReservatoin(Reservation r) {
        return rm.saveReservation(r);
    }
}
