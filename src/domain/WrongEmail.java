package domain;

public class WrongEmail extends Exception {
    public WrongEmail(String message) {
        super(message);
    }
}