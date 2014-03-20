package dataSource;

import MockObjects.ReservationMapperMock;
import domain.Reservation;
import java.sql.Date;
import java.util.HashMap;
import java.util.Map;
import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;

public class ReservationMapperMockTest {

    DBFacade dbf;

    Reservation reservationOne =
            new Reservation(1, 100, 20, new Date(01,01,2014), 4);
    Reservation reservationTwo = 
            new Reservation(2, 101, 30, new Date(01,01,2014), 4);
    Reservation reservationThree = 
            new Reservation(3, 102, 40, new Date(01,01,2014), 4);

    @Before
    public void init() {
        ReservationMapperMock resMock = new ReservationMapperMock();
        //dbf = new DBFacade(resMock);
        Map<Integer, Reservation> reservations = new HashMap<>();
        reservations.put(reservationOne.getID(), reservationOne);
        reservations.put(reservationTwo.getID(), reservationTwo);
        reservations.put(reservationThree.getID(), reservationThree);
        resMock.setReservations(reservations);
        
        
        
        
    }

    @Test
    public void testGetReservation() {
//        int ID = 1;
//        Reservation expResult = reservationOne;
//        Reservation result = dbf.getReservation(ID);
//        assertEquals(expResult.toString(), result.toString());
    }
}
