package dataSource;

import domain.Customer;
import java.sql.Connection;
import java.util.ArrayList;
import java.util.List;

public class CustomerMapper extends AbstractMapper implements CustomerMapperInterface {

    public CustomerMapper(Connection con) {
        super(con, "Customer", Customer.class);
    }

    @Override
    public Customer getCustomer(int ID) {
        List<Customer> customer = generalSearch("ID", "Fail in CustomerMapper - saveCustomer", ID);
        if (customer.isEmpty()) {
            return null;
        } else {
            return customer.get(0);
        }
    }

    @Override
    public int saveNewCustomer(Customer c) {
        int seq = getSequenceNumber("SELECT customerSeq.nextval "
                + "FROM dual",
                "Fail in CustomerMapper - saveCustomer");
        c.setID(seq);
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
                "Fail in CustomerMapper - getCustomer");
        if (customer.isEmpty()) {
            return null;
        } else {
            return customer;
        }
    }

    @Override
    public List<Customer> search(Object variable, String columnName) {
        return generalSearch(columnName,
                "Fail in Customer Mapper - Search ", variable);
    }
}
