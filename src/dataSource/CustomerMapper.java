package dataSource;

import domain.Customer;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class CustomerMapper implements CustomerMapperInterface {

    private final Connection con;

    public CustomerMapper(Connection con) {
        this.con = con;
    }

    @Override
    public Customer getCustomer(int ID) {
        Customer o = null;
        String SQLString1
                = "select * "
                + "from customer "
                + "where ID = ?";

        PreparedStatement statement = null;

        try {
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

        }
        catch (SQLException e) {
            System.out.println("Fail in CustomerMapper - getCustomer");
            System.out.println(e.getMessage());
        }
        finally // must close statement
        {
            try {
                if (statement != null) {
                    statement.close();
                }
            }
            catch (SQLException e) {
                System.out.println("Fail in CustomerMapper - getCustomer");
                System.out.println(e.getMessage());
            }
        }
        return o;
    }

    @Override
    public int saveNewCustomer(Customer c) {
        int rowsInserted = 0;
        String SQLString1
                = "select customerSeq.nextval  "
                + "from dual";
        String SQLString2
                = "insert into customer "
                + "values (?,?,?,?,?,?,?,?)";
        PreparedStatement statement = null;

        try {
            statement = con.prepareStatement(SQLString1);
            ResultSet rs = statement.executeQuery();
            if (rs.next()) {
                c.setID(rs.getInt(1));
            }

            //== insert tuple
            statement = con.prepareStatement(SQLString2);
            statement.setInt(1, c.getID());
            statement.setString(2, c.getAddres());
            statement.setString(3, c.getCountry());
            statement.setString(4, c.getFirst_name());
            statement.setString(5, c.getLast_name());
            statement.setString(6, c.getPhone());
            statement.setString(7, c.getEmail());
            statement.setString(8, c.getTravel_agency());

            rowsInserted = statement.executeUpdate();
        }
        catch (SQLException e) {
            System.out.println("Fail in CustomerMapper - saveReservation");
            System.out.println(e.getMessage());
        }
        finally // must close statement
        {
            try {
                statement.close();
            }
            catch (SQLException e) {
                System.out.println("Fail in CustomerMapper - saveReservation");
                System.out.println(e.getMessage());
            }
        }
        if (rowsInserted == 0) {
            return -1;
        }
        return c.getID();
    }
    
    @Override
    public List<Customer> getAllCustomers() {
        List<Customer> allCustomers = new ArrayList();
        String SQLString = "select * "
                + "from customer";
        PreparedStatement statement = null;
        try {
            statement = con.prepareStatement(SQLString);
            ResultSet rs = statement.executeQuery();
            while (rs.next()) {
                allCustomers.add(
                        new Customer(rs.getInt(1), rs.getString(2),
                        rs.getString(3), rs.getString(4), rs.getString(5),
                        rs.getString(6), rs.getString(7), rs.getString(8)));
            }
        } catch (SQLException e) {
            System.out.println("Fail in CustomerMapper - getAllCustomers");
            System.out.println(e.getMessage());
        } finally {
            try {
                if (statement != null) {
                    statement.close();
                }
            } catch (SQLException e) {
                System.out.println("Fail in CustomerMapper - getAllCustomers");
                System.out.println(e.getMessage());
            }
        }
        return allCustomers;
    }
}
