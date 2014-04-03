package domain;

public class UnpaidReservation
{
    private int ID;
    
    public UnpaidReservation(){
    }
    
    public UnpaidReservation(int ID){
        this.ID = ID;
    }
    
    public int getID(){
        return ID;
    }
    
    public void setID(int ID){
        this.ID = ID;
    }
}
