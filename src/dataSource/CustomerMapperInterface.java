package dataSource;

import domain.Customer;

public interface CustomerMapperInterface {

    int saveNewCustomer(Customer c);

    Customer getCustomer(int ID);
}
