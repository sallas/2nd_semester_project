package dataSource;

import domain.HotelUser;

public interface LoginMapperInterface {

    HotelUser getUsernameAndPassword(String username, String password);
    
}
