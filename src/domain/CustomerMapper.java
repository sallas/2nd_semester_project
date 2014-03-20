package domain;

import domain.Customer;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Kaloyan
 */
public class CustomerMapper
{
private final Connection con;

  public CustomerMapper(Connection con) {
    this.con = con;
  }

  //== load an order and the associated order details
 // @Override
  public Customer getCustomer( int ID) {
    Customer o = null;
    String SQLString1 = // get order
            "select * "
            + "from customer "
            + "where ID = ?";
    
    PreparedStatement statement = null;

    try {
      //=== get order
      statement = con.prepareStatement(SQLString1);
      statement.setInt(1, ID);     // primary key value
      ResultSet rs = statement.executeQuery();
      if (rs.next()) {
        o = new Customer(ID,
                rs.getString(2),
                rs.getString(3),
                rs.getString(4),
                rs.getString(5),
                rs.getString(6),
                rs.getString(7),
                rs.getString(8));
      }

    
    } catch (SQLException e) {
      System.out.println("Fail in CustomerMapper - getCustomer");
      System.out.println(e.getMessage());
    } finally // must close statement
    {
      try {
        if (statement != null) {
          statement.close();
        }
      } catch (SQLException e) {
        System.out.println("Fail in CustomerMapper - getCustomer");
        System.out.println(e.getMessage());
      }
    }
    return o;
  }

  //== Insert new order (tuple)
 // @Override
  public boolean saveNewCustomer(Customer o) {
    int rowsInserted = 0;
    String SQLString1
            = "select orderseq.nextval  "
            + "from dual";
    String SQLString2
            = "insert into orders "
            + "values (?,?,?,?,?,?,?,?)";
    PreparedStatement statement = null;

    try {
      //== get unique ono
      statement = con.prepareStatement(SQLString1);
      ResultSet rs = statement.executeQuery();
      if (rs.next()) {
        o.setID(rs.getInt(1));
      }

      //== insert tuple
      statement = con.prepareStatement(SQLString2);
      statement.setInt(1, o.getID());
      statement.setString(2, o.getAddres());
      statement.setString(3, o.getCountry());
      statement.setString(4, o.getFirst_name());
      statement.setString(5, o.getLast_name());
      statement.setString(6, o.getPhone());
      statement.setString(7, o.getEmail());
      statement.setString(8, o.getTravel_agency());
      
      rowsInserted = statement.executeUpdate();
      con.commit();
    } catch (SQLException e) {
      try {
        con.rollback();
        System.out.println("Fail in CustomerMapper - saveNewCustomer");
        System.out.println(e.getMessage());
      } catch (SQLException ex) {
        Logger.getLogger(CustomerMapper.class.getName()).log(Level.SEVERE, null, ex);
      }
    } finally // must close statement
    {
      try {
        statement.close();
      } catch (SQLException e) {
        System.out.println("Fail in CustomerMapper - saveNewCustomer");
        System.out.println(e.getMessage());
      }
    }
    return rowsInserted == 1;
  }
}
