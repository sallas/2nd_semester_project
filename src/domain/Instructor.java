package domain;

public class Instructor {
    private int ID;
    private int userID;

    public Instructor(int ID, int userID) {
        this.ID = ID;
        this.userID = userID;
    }

    public Instructor() {
    }

    public int getID() {
        return ID;
    }

    public void setID(int ID) {
        this.ID = ID;
    }

    public int getUserID() {
        return userID;
    }

    public void setUserID(int userID) {
        this.userID = userID;
    }

    @Override
    public String toString() {
        return "Instructor ID= " + ID + " | userID= " + userID;
    }
}
