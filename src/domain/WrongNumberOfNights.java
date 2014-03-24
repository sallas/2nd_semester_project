package domain;

/**
 *
 * @author martin
 * Exception class for entering negative number of nights.
 */
public class WrongNumberOfNights extends Exception {
    public WrongNumberOfNights(String message) {
        super(message);
    }
}
