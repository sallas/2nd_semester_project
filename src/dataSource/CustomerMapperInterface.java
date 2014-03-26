package dataSource;

import domain.Customer;
import java.util.List;

public interface CustomerMapperInterface {

    int saveNewCustomer(Customer c);

    Customer getCustomer(int ID);
    
    List<Customer> getAllCustomers();
}
