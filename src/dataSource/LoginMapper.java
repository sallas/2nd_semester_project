/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package dataSource;

import domain.HotelUser;
import domain.Reservation;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 *
 * @author Kaloyan
 */
public class LoginMapper {
    private final Connection con;

    public LoginMapper(Connection con) {
        this.con = con;
    }



 
    public HotelUser getUsernameAndPassword(String username, String password   ) {
      HotelUser h =null;
        String SQLString = "select * "
                + "from HOTEL_USER "
                + "where Username = ? and Psw = ?";
        PreparedStatement statement = null;
        
        try {
            statement = con.prepareStatement(SQLString);
            statement.setString(1, username);
            statement.setString(2, password);
            ResultSet rs = statement.executeQuery();
            if (rs.next()) {
                h = new HotelUser();
                        h.setUsername(rs.getString(1));
                        h.setPsw(rs.getString(2));
                System.out.println(h);
            }
        } catch (SQLException e) {
            System.out.println("Fail in LoginMapper - get UsernameAndPassword");
            System.out.println(e.getMessage());
        } finally {
            try {
                if (statement != null) {
                    statement.close();
                }
            } catch (SQLException e) {
                System.out.println("Fail in LoginMapper - getUsernameAndPassword");
                System.out.println(e.getMessage());
            }
        }
        System.out.println(h);
        return h;
       
    }

}
