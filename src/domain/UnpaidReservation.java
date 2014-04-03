package domain;

import java.sql.Date;

public class UnpaidReservation
{
    private int ID;
    private Date bookingDate;
    
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
    
    public Date getBookingDate(){
        return bookingDate;
    }
    
    public void setBookingDate(Date bookingDate){
        this.bookingDate = bookingDate;
    }
}
