package dataSource;

import domain.Customer;
import java.sql.Connection;
import java.util.ArrayList;
import java.util.List;

public class CustomerMapper extends AbstractMapper implements CustomerMapperInterface {

    public CustomerMapper(Connection con) {
        super(con);
    }

    @Override
    public Customer getCustomer(int ID) {
        List<Customer> customer = search(ID, "id");
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
                "Fail in CustomerMapper - saveCustomer");
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
                "Fail in CustomerMapper - getCustomer");
        if (customer.isEmpty()) {
            return null;
        } else {
            return customer;
        }
    }

    @Override
    public List<Customer> search(Object variable, String columnName) {
        return generalSearch(Customer.class, "Customer", columnName,
                "Fail in Customer Mapper - Search ", variable);
    }

    @Override
    public List<Customer> search(Object variable, String columnName, String exMessage) {
        return generalSearch(Customer.class, "Customer", columnName,
                exMessage, variable);
    }
}
