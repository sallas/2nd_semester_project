package domain;

import dataSource.ReservationFixture;
import dataSource.RoomMapper;
import dataSource.TestDBConnector;
import java.sql.Connection;
import java.sql.Date;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.junit.Test;
import static org.junit.Assert.*;
import org.junit.After;
import org.junit.Before;

/**
 *
 * @author martin
 */
public class ControllerTest {
    
    Connection con;
    private Controller controller;
    TestDBConnector connector = new TestDBConnector();
    
    
    public ControllerTest() {
    }
    
    @Before
    public void setUp() {
        con = connector.getConnection();
        controller = Controller.getInstance();
        ReservationFixture.setUp(con);
    }
    
    @After
    public void tearDown() {
        if (con != null) try {
            con.close();
        } catch (SQLException ex) {
            Logger.getLogger(ControllerTest.class.getName()).log(Level.SEVERE, 
                    null, ex);
        }
    }
    
    /*
     * Test saving of a reservation to the database with proper information.
     */
    @Test
    public void saveReservationInControllerProperFull() 
            throws WrongNumberOfNights, WrongEmail {
        boolean res = controller.createNewReservation("Igor", "the Russian", "Syberia", "notRussia", 
                      "00000", "igor@gmail.com", "sunshine", new Date(1994, 12, 31), 30, 100);
        assertTrue(res);
    }
    
    /*
     * Test saving of a reservation with agency set to null.
     * In Oracle empty string is automatically changed to null.
     */
    @Test
    public void saveReservationInControllerProperNoAgency() 
            throws WrongNumberOfNights, WrongEmail{
        boolean res = controller.createNewReservation("Igor", "the Russian", "Syberia", "notRussia", 
                "00000", "igor@gmail.com", "", new Date(1994, 12, 31), 30, 100);
        assertTrue(res);
    }
    
    /*
     * Test saving with negative number of nights.
     */
    @Test(expected = WrongNumberOfNights.class)
    public void saveReservationInControllerNegativeNumberOfNights() 
            throws WrongNumberOfNights, WrongEmail{
        boolean res = controller.createNewReservation("Igor", "the Russian", "Syberia", "notRussia", 
                "00000", "igor@gmail.com", "sunshine", new Date(1994, 12, 31), -1, 101);
        assertFalse(res);
    }
    
    /*
     * Test saving wrong email.
     */
    
    @Test(expected = WrongEmail.class)
    public void saveReservationInControllerWrongEmail() 
            throws WrongNumberOfNights, WrongEmail{
        boolean res = controller.createNewReservation("Igor", "the Russian", "Syberia", "notRussia", 
                "00000", "igorsdscom", "sunshine", new Date(1994, 12, 31), 2, 101);
        assertFalse(res);
    }
    
    /*
     * Room id does not exist.
     */
    @Test
    public void saveReservationInControllerWrongRoomID() throws WrongNumberOfNights, WrongEmail{
        boolean res = controller.createNewReservation("Igor", "the Russian", "Syberia", "notRussia", 
                "00000", "igor@gmail.com", "sunshine", new Date(1994, 12, 31), 2, 20);
        assertFalse(res);
    }
}