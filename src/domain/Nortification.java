package domain;

public class Nortification {
    private int ID;
    private String message;

    public Nortification(int ID, String message) {
        this.ID = ID;
        this.message = message;
    }

    public int getID() {
        return ID;
    }

    public String getMessage() {
        return message;
    }

    public void setID(int ID) {
        this.ID = ID;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
