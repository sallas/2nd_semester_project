/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dataSource;

import domain.Facility;
import domain.HotelUser;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Kaloyan
 */
public class AdministratorMapper {

    private final Connection con;

    public AdministratorMapper(Connection con) {
        this.con = con;
    }

    public boolean addSportFacilities(Facility f) {
     
        int rowsInserted = 0;
        String SQLString1
                = "insert into facility "
                + "values (?,?,?,?,?,?,?)";
        PreparedStatement statement = null;
        try {
            
            statement = con.prepareStatement(SQLString1);
           
          

            //== insert tuple
            statement = con.prepareStatement(SQLString1);
            statement.setInt(1, f.getID());
            statement.setString(2, f.getName());
            statement.setString(3, f.getType());
            statement.setInt(4, f.getCapacity());
            statement.setBoolean(5, f.isHasWaitingList());
            statement.setBoolean(6, f.isHasBooking());
            statement.setBoolean(7, f.isHasInstructor());
            rowsInserted = statement.executeUpdate(); 
            con.commit();
        } catch (SQLException e) {
            try {
                con.rollback();
                System.out.println("Fail in  AdministratorMapper - addSportFacility1");
                System.out.println(e.getMessage());
            } catch (SQLException ex) {
                System.out.println("fail in AdministratorMapper - addSportFacility2");
            }
        } finally // must close statement
        {
            try {
                statement.close();
            } catch (SQLException e) {
                System.out.println("Fail in AdministratorMapper - addSportFacilit3");
                System.out.println(e.getMessage());
            }
        }
        return rowsInserted == 1;
    }







 public boolean deleteSportFacilities(Facility f) {
        int rowsDeleted = 0;
        String SQLString1
                = "delete from facility "
                + "where ID =?";
        PreparedStatement statement = null;
        try {
            
            statement = con.prepareStatement(SQLString1);
                        

            //== insert tuple
            statement = con.prepareStatement(SQLString1);
            statement.setInt(1, f.getID());
            rowsDeleted = statement.executeUpdate(); 
            con.commit();
        } catch (SQLException e) {
            try {
                con.rollback();
                System.out.println("Fail in  AdministratorMapper - deleteSportFacility1");
                System.out.println(e.getMessage());
            } catch (SQLException ex) {
                System.out.println("fail in AdministratorMapper - deleteSportFacility2");
            }
        } finally // must close statement
        {
            try {
                statement.close();
            } catch (SQLException e) {
                System.out.println("Fail in AdministratorMapper - deleteSportFacility3");
                System.out.println(e.getMessage());
            }
        }
        return rowsDeleted == 1;
    }




public boolean addAccount(HotelUser a) {
        int rowsInserted = 0;
        String SQLString1
                = "insert into HotelUser "
                + "values (?,?,?,?,?,?)";
        PreparedStatement statement = null;
        try {
            
            statement = con.prepareStatement(SQLString1);
            

            //== insert tuple
            statement = con.prepareStatement(SQLString1);
            statement.setInt(1, a.getId());
            statement.setString(2, a.getUsername());
            statement.setString(3, a.getPsw());
            statement.setString(4, a.getStatus());
            statement.setInt(5, a.getReservation_id());
            statement.setInt(6, a.getSpent());
            rowsInserted = statement.executeUpdate(); 
            con.commit();
        } catch (SQLException e) {
            try {
                con.rollback();
                System.out.println("Fail in  AdministratorMapper - addAccount1");
                System.out.println(e.getMessage());
            } catch (SQLException ex) {
                System.out.println("fail in AdministratorMapper - addAccount2");
            }
        } finally // must close statement
        {
            try {
                statement.close();
            } catch (SQLException e) {
                System.out.println("Fail in AdministratorMapp2er - addAccount3");
                System.out.println(e.getMessage());
            }
        }
        return rowsInserted == 1;
    }




public boolean deleteAccount(HotelUser a ) {
        int rowsInserted = 0;
        String SQLString1
                = "delete from HotelUser "
                + "where ID =?";
        PreparedStatement statement = null;
        try {
            
            statement = con.prepareStatement(SQLString1);

            //== insert tuple
            statement = con.prepareStatement(SQLString1);
            statement.setInt(1, a.getId());
            rowsInserted = statement.executeUpdate(); 
            con.commit();
        } catch (SQLException e) {
            try {
                con.rollback();
                System.out.println("Fail in  AdministratorMapper - deleteSportFacility1");
                System.out.println(e.getMessage());
            } catch (SQLException ex) {
                System.out.println("fail in AdministratorMapper - deleteSportFacility2");
            }
        } finally // must close statement
        {
            try {
                statement.close();
            } catch (SQLException e) {
                System.out.println("Fail in AdministratorMapper - deleteSportFacilit3");
                System.out.println(e.getMessage());
            }
        }
        return rowsInserted == 1;
    }










}
