package domain;

public class Room {

    private int ID;
    private String type;
    
    public Room() {}

    public Room(int ID, String type) {
        this.ID = ID;
        this.type = type;
    }

    public int getID() {
        return ID;
    }

    public void setID(int ID) {
        this.ID = ID;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    @Override
    public String toString() {
        return "Room Number= " + ID + " | type= " + type;
    }
}
