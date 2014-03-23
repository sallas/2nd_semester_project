package dataSource;

import domain.Customer;

public interface CustomerMapperInterface {
    int saveNewCustomer(Customer o);
    Customer getCustomer( int ID);
}
