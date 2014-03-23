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
    // Creation of reservation to use in tests
    Reservation reservationOne =
            new Reservation(1, 100, 20, new Date(01,01,2014), 4);
    Reservation reservationTwo = 
            new Reservation(2, 101, 30, new Date(01,01,2014), 4);
    Reservation reservationThree = 
            new Reservation(3, 102, 40, new Date(01,01,2014), 4);

    /*
    * Sets up the mock object before each set, handing it to the DBFacade
    * changes it behavior to use the mock object instead of the database
    */
    @Before
    public void init() {
        ReservationMapperMock resMock = new ReservationMapperMock();
        dbf = new DBFacade(resMock);
        Map<Integer, Reservation> reservations = new HashMap<>();
        reservations.put(reservationOne.getID(), reservationOne);
        reservations.put(reservationTwo.getID(), reservationTwo);
        reservations.put(reservationThree.getID(), reservationThree);
        resMock.setReservations(reservations);
    }
    
    
    /*
    * Checks so the dbf return the reservation with the correct ID
    */
    @Test
    public void testGetReservationMatchingID() {
        int ID = 1;
        Reservation expResult = reservationOne;
        Reservation result = dbf.getReservation(ID);
        assertEquals(expResult.toString(), result.toString());
    }
    
    /*
    * Checks so no reservation is returned when bad ID is given
    */
    @Test
    public void testGetReservationNotMatchingID() {
        int ID = 99;
        Reservation result = dbf.getReservation(ID);
        assertTrue(result == null);
    }
    
    /*
    * Checks so the reservation is inserted when reservation ID
    * doesn't exist in another reservation
    */
//    @Test
//    public void testSaveReservationNoConflict()
//    {
//        Reservation reservation = 
//            new Reservation(4, 102, 40, new Date(01,01,2014), 4);
//        boolean status = dbf.saveReservatoin(reservation);
//        assertTrue(status);
//    }
    
    
    /*
    * Checks so the reservation is not inserted when the ID exists
    * in another reservation.
    */
//    @Test (expected = AssertionError.class)
//    public void testSaveReservationWithConflict()
//    {
//        Reservation reservation = 
//            new Reservation(3, 102, 40, new Date(01,01,2014), 4);
//        boolean status = dbf.saveReservatoin(reservation);
//        assertFalse(status);
//    }
}
