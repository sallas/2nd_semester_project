package domain;

public class HotelUser {

    private int id;
    private String username;
    private String psw;
    private String status;
    private int reservation_id;
    private int spent;

    public HotelUser(int id, String username, String psw, String status, int reservation_id, int spent) {
        this.id = id;
        this.username = username;
        this.psw = psw;
        this.status = status;
        this.reservation_id = reservation_id;
        this.spent = spent;
    }

    public HotelUser() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPsw() {
        return psw;
    }

    public void setPsw(String psw) {
        this.psw = psw;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public int getReservation_id() {
        return reservation_id;
    }

    public void setReservation_id(int reservation_id) {
        this.reservation_id = reservation_id;
    }

    public int getSpent() {
        return spent;
    }

    public void setSpent(int spent) {
        this.spent = spent;
    }

    @Override
    public String toString() {
        return "HotelUser ID= " + id + " | username= " + username
                + " | psw= " + psw + ", status= " + status
                + " | Reservation_ID= " + reservation_id
                + " | spent= " + spent;
    }

}
