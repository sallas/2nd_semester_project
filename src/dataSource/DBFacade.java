package dataSource;

import domain.Reservation;

public class DBFacade {

    private static ReservationMapperInterface reservationMapper;
    private static DBFacade instance;

    private DBFacade() {
        reservationMapper = new ReservationMapper(DBConnector.getConnection());
    }
    
    public DBFacade(ReservationMapperInterface resMapper){
      reservationMapper = resMapper;
    }

    public static DBFacade getInstance() {
        return instance;
    }

    public Reservation getReservation(int ID) {
        return reservationMapper.getReservation(ID);
    }

    public boolean saveReservatoin(Reservation r) {
        return reservationMapper.saveReservation(r);
    }
}
