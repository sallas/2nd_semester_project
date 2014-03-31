package domain;

public class Facility {
    private int ID;
    private String name;
    private String type;
    private int capacity;
    private boolean hasWaitingList;
    private boolean hasBooking;
    private boolean hasInstructor;


    public int getID() {
        return ID;
    }

    public String getName() {
        return name;
    }

    public String getType() {
        return type;
    }

    public int getCapacity() {
        return capacity;
    }

    public boolean isHasWaitingList() {
        return hasWaitingList;
    }

    public boolean isHasBooking() {
        return hasBooking;
    }

    public boolean isHasInstructor() {
        return hasInstructor;
    }

    public void setID(int ID) {
        this.ID = ID;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setType(String type) {
        this.type = type;
    }

    public void setCapacity(int capacity) {
        this.capacity = capacity;
    }

    public void setHasWaitingList(boolean hasWaitingList) {
        this.hasWaitingList = hasWaitingList;
    }

    public void setHasBooking(boolean hasBooking) {
        this.hasBooking = hasBooking;
    }

    public void setHasInstructor(boolean hasInstructor) {
        this.hasInstructor = hasInstructor;
    }

    public Facility(int ID, String name, String type, int capacity, boolean hasWaitingList, boolean hasBooking, boolean hasInstructor) {
        this.ID = ID;
        this.name = name;
        this.type = type;
        this.capacity = capacity;
        this.hasWaitingList = hasWaitingList;
        this.hasBooking = hasBooking;
        this.hasInstructor = hasInstructor;
    }
    public Facility(){
    }

    @Override
    public String toString() {
        return "Facility " + "name: " + name + " , type: " + type + " , capacity: " + capacity;
    }
}
