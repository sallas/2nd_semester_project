package domain;

import dataSource.DBFacade;
import java.sql.Date;

public class Controller {
    private DBFacade facade;
    private EmailValidator emailValidator;
    private static Controller instance = null;

    /*
     * Init a controller.
     */
    private Controller() {
        facade = DBFacade.getInstance();
        emailValidator = new EmailValidator();
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
    public boolean createNewReservation(String firstName, String familyName,
                                        String address, String country,
                                        String phone, String email,
                                        String agency, Date checkin,
                                        int nights, int roomID) 
            throws WrongNumberOfNights, WrongEmail {
        if (nights <= 0){
            throw new WrongNumberOfNights("Zero or less than zero"
                      + "number of nights");
        }
        if (!emailValidator.validate(email)){
            throw new WrongEmail("Email is wrong.");
        }
        Customer customer = new Customer(-1 , address, country, firstName,
                                         familyName, phone, email, agency);
        Reservation reservation = new Reservation(-1, roomID,
                                         -1, checkin, nights);
        return facade.saveReservationInformation(reservation, customer);
    }
}
