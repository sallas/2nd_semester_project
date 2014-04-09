/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package dataSource;

import domain.Customer;
import domain.Facility;
import domain.HotelUser;
import java.sql.Connection;

/**
 *
 * @author Kaloyan
 */
public class AdministratorMapper extends AbstractMapper 
{

    public AdministratorMapper(Connection con, String tableName, Class classType) {
        super(con, tableName, classType);
    }
    
    
       public boolean deleteAccount(HotelUser a) {
        return 1 == executeSQLInsert(
                "DELETE FROM HOTEL_USER WHERE ID = ?",
                "Fail in AdministratorMapper - delete Account",
                a.getId());

    }

    public boolean addAccount(HotelUser a) {
        return 1 == executeSQLInsert(
                "insert into HotelUser values (?,?,?,?,?,?) ",
                "Fail in AdministratorMapper - addAccount",
                a.getId(), a.getUsername(), a.getPsw(),
                a.getStatus(), a.getSpent());

    }

    ;



  public boolean deleteSportFacility(Facility f) {
        return 1 == executeSQLInsert(
                "DELETE FROM facility WHERE ID = ?",
                "Fail in AdministratorMapper - deleteSportFacility",
                f.getID());

    }

    public boolean addSportFacility(Facility f) {
        return 1 == executeSQLInsert(
                "insert into facility values (?,?,?,?,?,?) ",
                "Fail in AdministratorMapper - addAccount",
                f.getID(), f.getName(), f.getType(),
                f.isHasBooking(), f.getCapacity(), f.isHasInstructor(), f.isHasWaitingList());
    }

    public boolean updateUername(HotelUser a) {
        return 1 == executeSQLInsert(
                "UPDATE HOTEL_USER"
                + "Set username=?"
                + "WHERE ID = ? ",
                "Fail in AdministratorMapper - updateUsername",
                a.getId(), a.getUsername());

    }

    public boolean updatePassword(HotelUser a) {
        return 1 == executeSQLInsert(
                "UPDATE HOTEL_USER"
                + "Set psw=?"
                + "WHERE ID = ? ",
                "Fail in AdministratorMapper - updatePassword",
                a.getId(), a.getPsw());
    }

    public boolean updateStatus(HotelUser a) {
        return 1 == executeSQLInsert(
                "UPDATE HOTEL_USER"
                + "Set status=?"
                + "WHERE ID = ? ",
                "Fail in AdministratorMapper - updateStatus",
                a.getId(), a.getStatus());
    }

    public boolean updateReservation_id(HotelUser a) {
        return 1 == executeSQLInsert(
                "UPDATE HOTEL_USER"
                + "Set Reservation_id=?"
                + "WHERE ID = ? ",
                "Fail in AdministratorMapper - updateReservation_id",
                a.getId(), a.getReservation_id());
    }

    public boolean updateSpent(HotelUser a) {
        return 1 == executeSQLInsert(
                "UPDATE HOTEL_USER"
                + "Set Spent=?"
                + "WHERE ID = ? ",
                "Fail in AdministratorMapper - updateSpent",
                a.getId(), a.getSpent());
    }

    public boolean updateAddress(Customer a) {
        return 1 == executeSQLInsert(
                "UPDATE HOTEL_USER"
                + "Set Address=?"
                + "WHERE ID = ? ",
                "Fail in AdministratorMapper - updateAddress",
                a.getID(), a.getAddres());
    }

    public boolean updateCountry(Customer a) {
        return 1 == executeSQLInsert(
                "UPDATE HOTEL_USER"
                + "Set County=?"
                + "WHERE ID = ? ",
                "Fail in AdministratorMapper - updateCountry",
                a.getID(), a.getCountry());
    }

    public boolean updateFirst_name(Customer a) {
        return 1 == executeSQLInsert(
                "UPDATE HOTEL_USER"
                + "Set First_name=?"
                + "WHERE ID = ? ",
                "Fail in AdministratorMapper - updateFirst_name",
                a.getID(), a.getFirst_name());
    }

    public boolean updateLast_name(Customer a) {
        return 1 == executeSQLInsert(
                "UPDATE HOTEL_USER"
                + "Set Last_name=?"
                + "WHERE ID = ? ",
                "Fail in AdministratorMapper - updateLast_name",
                a.getID(), a.getFirst_name());
    }

    public boolean updatePhone(Customer a) {
        return 1 == executeSQLInsert(
                "UPDATE HOTEL_USER"
                + "Set Phone=?"
                + "WHERE ID = ? ",
                "Fail in AdministratorMapper - updatePhone",
                a.getID(), a.getPhone());
    }

    public boolean updateEmail(Customer a) {
        return 1 == executeSQLInsert(
                "UPDATE HOTEL_USER"
                + "Set Email=?"
                + "WHERE ID = ? ",
                "Fail in AdministratorMapper - updateEmail",
                a.getID(), a.getEmail());
    }

    public boolean updateTravel_agency(Customer a) {
        return 1 == executeSQLInsert(
                "UPDATE HOTEL_USER"
                + "Set Travel_agency=?"
                + "WHERE ID = ? ",
                "Fail in AdministratorMapper - updateTravel_agency",
                a.getID(), a.getTravel_agency());
    }

}

    
    
    
    

