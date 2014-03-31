package dataSource;

import domain.HotelUser;
import java.sql.Connection;
import java.util.List;

public class HotelUserMapper extends AbstractMapper implements HotelUserMapperInterface{

    public HotelUserMapper(Connection con) {
        super(con);
    }
    
    /*
    * Returns a list of all hotelUsers in the database
    */
    @Override
    public List<HotelUser> getAllUsers(){
        return executeQueryAndGatherResults(HotelUser.class,
                "SELECT * FROM hotel_user", 
                "Fail in HotelUserMapper - getAllUsers",
                new String[]{"id", "username", "psw", "status", "reservation_id", "spent"},
                new int[]{0, 1, 1, 1, 0, 0});
    }
    
    /*
    * Returns one hoteluser
    */
    @Override
    public List<HotelUser> getUser(int id){
        return executeQueryAndGatherResults(HotelUser.class,
                "SELECT * FROM hotel_user WHERE ID = ?", 
                "Fail in HotelUserMapper - getAllUsers",
                new String[]{"id", "username", "psw", "status", "reservation_id", "spent"},
                new int[]{0, 1, 1, 1, 0, 0}, id);
    }
}
