package MockObjects;

import dataSource.CustomerMapperInterface;
import domain.Customer;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class CustomerMapperMock implements CustomerMapperInterface {

    private Map<Integer, Customer> customers;
    private int sequence;

    public CustomerMapperMock() {
        customers = new HashMap<>();
        sequence = 1;
    }

    public void setCustomers(Map<Integer, Customer> customers) {
        this.customers = customers;
    }

    public void setSequence(int sequence) {
        this.sequence = sequence;
    }

    @Override
    public int saveNewCustomer(Customer c) {
        c.setID(sequence);
        customers.put(sequence, c);
        return sequence++;
    }

    @Override
    public Customer getCustomer(int ID) {
        Customer c = null;
        if (customers.containsKey(ID)) {
            c = customers.get(ID);
        }
        return c;
    }

    @Override
    public List<Customer> getAllCustomers() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public List<Customer> search(Object variable, String columnName) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public List<Customer> search(Object variable, String columnName, String exMessage) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

}
