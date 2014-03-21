/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package domain;

import org.junit.Test;
import static org.junit.Assert.*;
import org.junit.After;
import org.junit.Before;

/**
 *
 * @author martin
 */
public class ControllerTest {
    
    private Controller controller;
    
    public ControllerTest() {
    }
    
    @Before
    public void setUp() {
        controller = Controller.getInstance();
    }
    
    @After
    public void tearDown() {
    }
    
    /*
     * Test saving of a reservation to the database.
     */
    @Test
    public void saveReservationInController() {
    }
}