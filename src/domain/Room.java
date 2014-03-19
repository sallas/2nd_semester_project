package domain;

public class Room 
{
    int ID;
    String type;

    public Room(int ID, String type)
    {
        this.ID = ID;
        this.type = type;
    }

    public int getID()
    {
        return ID;
    }

    public void setID(int ID)
    {
        this.ID = ID;
    }

    public String getType()
    {
        return type;
    }

    public void setType(String type)
    {
        this.type = type;
    }

    @Override
    public String toString()
    {
        return "Room " + "ID: " + ID + ", type: " + type;
    }
}
