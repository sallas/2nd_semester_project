package dataSource;

import domain.HotelUser;
import java.util.List;

interface HotelUserMapperInterface {
    
    List<HotelUser> getAllUsers();
    
    List<HotelUser> getUser(int id);
    
    List<HotelUser> search(Object variable, String columnName);
}
