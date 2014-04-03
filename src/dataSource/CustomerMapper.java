package dataSource;

import domain.Customer;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class CustomerMapper extends AbstractMapper implements CustomerMapperInterface {

    public CustomerMapper(Connection con) {
        super(con);
    }

    @Override
    public Customer getCustomer(int ID) {
        ArrayList<Customer> customer = executeQueryAndGatherResults(
                Customer.class,
                "SELECT * FROM customer WHERE ID = ?",
                "Fail in CustomerMapper - getCustomer",
                new String[]{"ID", "addres", "country", "first_name", "last_name", "phone", "email", "travel_agency"},
                new int[]{DataType.INT, DataType.STRING, DataType.STRING, DataType.STRING, DataType.STRING, DataType.STRING, DataType.STRING, DataType.STRING},
                ID);
        if (customer.isEmpty()) {
            return null;
        } else {
            return customer.get(0);
        }
    }

    @Override
    public int saveNewCustomer(Customer c) {
        ArrayList<Customer> seq = executeQueryAndGatherResults(
                Customer.class,
                "SELECT customerSeq.nextval "
                + "FROM dual",
                "Fail in CustomerMapper - saveCustomer",
                new String[]{"ID"}, new int[]{DataType.INT});
        c.setID(seq.get(0).getID());
        if (c.getTravel_agency() == null) {
            c.setTravel_agency(new String());
        }
        int result = executeSQLInsert(
                "INSERT INTO customer VALUES (?, ?, ?, ?, ?, ?, ?, ?)",
                "Fail in CustomerMapper - saveCustomer",
                c.getID(), c.getAddres(), c.getCountry(),
                c.getFirst_name(), c.getLast_name(),
                c.getPhone(), c.getEmail(), c.getTravel_agency());
        if (result == 0) {
            return -1;
        }
        return c.getID();
    }

    @Override
    public List<Customer> getAllCustomers() {
        ArrayList<Customer> customer = executeQueryAndGatherResults(
                Customer.class,
                "SELECT * FROM customer",
                "Fail in CustomerMapper - getCustomer",
                new String[]{"ID", "addres", "country", "first_name", "last_name", "phone", "email", "travel_agency"},
                new int[]{DataType.INT, DataType.STRING, DataType.STRING, DataType.STRING, DataType.STRING, DataType.STRING, DataType.STRING, DataType.STRING});
        if (customer.isEmpty()) {
            return null;
        } else {
            return customer;
        }
    }

    @Override
    public List<Customer> search(Object variable, String columnName) {
        return executeQueryAndGatherResults(
                Customer.class,
                "SELECT * FROM customer "
                + "WHERE " + columnName + " = ?",
                "Fail in CustomerMapper - search ",
                new String[]{"ID", "addres", "country", "first_name",
                    "last_name", "phone", "email", "travel_agency"},
                new int[]{DataType.INT, DataType.STRING, DataType.STRING,
                    DataType.STRING, DataType.STRING, DataType.STRING,
                    DataType.STRING, DataType.STRING},
                variable);
    }
}
