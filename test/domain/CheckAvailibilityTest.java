package domain;

import dataSource.ReservationFixture;
import dataSource.TestDBConnector;
import java.sql.Connection;
import java.util.Calendar;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;

public class CheckAvailibilityTest {

    Controller controller;
    TestDBConnector connector = new TestDBConnector();
    ;
    Connection con;

    public CheckAvailibilityTest() {
        controller = Controller.getInstance();

    }

    @Before
    public void setUp() {
        con = connector.getConnection();
        ReservationFixture.setUp(con);
    }

    @After
    public void tearDown() {
        connector.releaseConnection(con);
    }

    @Test
    public void testRoomUnavailbleWhenNoRoomIsAvailble() {
        Calendar arrivalDate = Calendar.getInstance();
        arrivalDate.clear();
        arrivalDate.set(2014, 01, 02);
        Calendar departureDate = (Calendar) arrivalDate.clone();
        departureDate.add(Calendar.DATE, 2);
        int roomID = controller.getAvailableRoomOfSpecificType("single", arrivalDate, departureDate);
        System.out.println(roomID);
        assertTrue(roomID == -1);
    }

    @Test
    public void testRoomAvailbleWhenAvaibleRoomOnSpecifiedDateSingleRoom() {
        Calendar arrivalDate = Calendar.getInstance();
        arrivalDate.clear();
        arrivalDate.set(2015, 03, 02);
        Calendar departureDate = (Calendar) arrivalDate.clone();
        departureDate.add(Calendar.DATE, 2);
        int roomID = controller.getAvailableRoomOfSpecificType("single", arrivalDate, departureDate);
        System.out.println(roomID);
        assertTrue(roomID == 101);
    }
    
    @Test
    public void testRoomAvailbleWhenAvaibleRoomOnSpecifiedDateDoubleRoom() {
        Calendar arrivalDate = Calendar.getInstance();
        arrivalDate.clear();
        arrivalDate.set(2015, 03, 02);
        Calendar departureDate = (Calendar) arrivalDate.clone();
        departureDate.add(Calendar.DATE, 2);
        int roomID = controller.getAvailableRoomOfSpecificType("double", arrivalDate, departureDate);
        System.out.println(roomID);
        assertTrue(roomID == 100);
    }
}
