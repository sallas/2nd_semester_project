package dataSource;

import domain.HotelUser;
import java.sql.Connection;
import java.util.List;

public class LoginMapper extends AbstractMapper implements LoginMapperInterface {

    Connection con;

    public LoginMapper(Connection con) {
        super(con, "hotel_user", HotelUser.class);
    }

    @Override
    public HotelUser getUsernameAndPassword(String username, String password) {
        List<HotelUser> users
                = executeQueryAndGatherResults(
                        classType, "select * from HOTEL_USER "
                        + "where Username = ? and Psw = ?",
                        "Fail in LoginMapper - get UsernameAndPassword",
                        username, password);
        if(users.isEmpty()) {
            return null;
        } else {
            return users.get(0);
        }
    }
}
