package domain;

import dataSource.DBFacade;
import java.sql.Date;

public class Controller {
    private DBFacade facade;
    private Reservation currentReservation;
    private static Controller instance = null;

    /*
     * Init a controller.
     */
    private Controller() {
        facade = DBFacade.getInstance();
        currentReservation = new Reservation(0, 0, 0, null, 0);
    }
    
    public static Controller getInstance(){
        if (instance == null){
            instance = new Controller();
        }
        return instance;
    }
    
    /*
     * Create a new reservation in the controller and returns true on success.
     */
    public boolean createNewReservation() {
        //return facade.saveReservatoin();
        return true;
    }
}
