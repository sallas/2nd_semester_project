package domain;

public class Customer {

    private int ID;
    private String addres;
    private String country;
    private String first_name;
    private String last_name;
    private String phone;
    private String email;
    private String travel_agency = "";

    public int getID() {
        return ID;
    }

    public void setID(int ID) {
        this.ID = ID;
    }

    public String getAddres() {
        return addres;
    }

    public void setAddres(String addres) {
        this.addres = addres;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public String getFirst_name() {
        return first_name;
    }

    public void setFirst_name(String first_name) {
        this.first_name = first_name;
    }

    public String getLast_name() {
        return last_name;
    }

    public void setLast_name(String last_name) {
        this.last_name = last_name;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getTravel_agency() {
        return travel_agency;
    }

    public void setTravel_agency(String travel_agency) {
        this.travel_agency = travel_agency;
    }

    @Override
    public String toString() {
        return "Customer ID= " + ID + " | address= " + addres + " | country= "
                + country + " | First Name= " + first_name + " | Last Name= "
                + last_name + " | Phone number= " + phone
                + " | Email= " + email + " | Travel Agency= " + travel_agency;
    }

    public Customer(int ID, String addres, String country, String first_name,
            String last_name, String phone, String email, String travel_agency) {
        this.ID = ID;
        this.addres = addres;
        this.country = country;
        this.first_name = first_name;
        this.last_name = last_name;
        this.phone = phone;
        this.email = email;
        this.travel_agency = travel_agency;
    }

    public Customer() {
    }

}
