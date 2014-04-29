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
import java.util.ArrayList;
import oracle.net.aso.i;

/**
 *
 * @author Kaloyan
 */
public class AdministratorMapper extends AbstractMapper {

    public AdministratorMapper(Connection con) {
        super(con, "HOTEL_USER", AdministratorMapper.class);
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

    public boolean deleteSportFacility(Facility f) {
        return 1 == executeSQLInsert(
                "DELETE FROM facility WHERE ID = ?",
                "Fail in AdministratorMapper - deleteSportFacility",
                f.getID());

    }

    ;

    public boolean addSportFacility(Facility f) {
        return 1 == executeSQLInsert(
                "insert into facility values (?,?,?,?,?,?) ",
                "Fail in AdministratorMapper - addAccount",
                f.getID(), f.getName(), f.getType(),
                f.isHasBooking(), f.getCapacity(), f.isHasInstructor(), f.isHasWaitingList());
    }

    ;

    public boolean updateUsername(ArrayList<HotelUser> hotelUser) {
        int usernameUpdated = 0;
        for (int i = 0; i < hotelUser.size(); i++) {

            HotelUser user = hotelUser.get(i);
            return 1 == executeSQLInsert(
                    "UPDATE HOTEL_USER"
                    + " SET username=?"
                    + " WHERE ID = ? ",
                    "Fail in AdministratorMapper - updateUsername",
                    user.getUsername(), user.getId());//update user based on his ID with his new Password from user OBJECT
        }
        return (usernameUpdated == hotelUser.size());
    }

    public boolean updatePassword(ArrayList<HotelUser> hotelUser) {
        int passwordUpdated = 0;
        for (int i = 0; i < hotelUser.size(); i++) {

            HotelUser user = hotelUser.get(i);
            return 1 == executeSQLInsert(
                    "UPDATE HOTEL_USER"
                    + " SET Psw=?"
                    + " WHERE ID = ? ",
                    "Fail in AdministratorMapper - updateUsername",
                    user.getPsw(), user.getId());//update user based on his ID with his new Password from user OBJECT
        }
        return (passwordUpdated == hotelUser.size());
    }

    public boolean updateStatus(ArrayList<HotelUser> hotelUser) {
        int statusUpdated = 0;
        for (int i = 0; i < hotelUser.size(); i++) {

            HotelUser user = hotelUser.get(i);
            return 1 == executeSQLInsert(
                    "UPDATE HOTEL_USER"
                    + " SET Status=?"
                    + " WHERE ID = ? ",
                    "Fail in AdministratorMapper - updateUsername",
                    user.getStatus(), user.getId());//update user based on his ID with his new Password from user OBJECT
        }
        return (statusUpdated == hotelUser.size());
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

    public void lockHotelUser() {
        executeSQLQuery(
                "LOCK TABLE HOTEL_USER in exclusive mode",
                "Fail in ReservationMapper - lockReservationTable"
        );
    }

}
