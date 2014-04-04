package dataSource;

import domain.HotelUser;
import java.sql.Connection;
import java.util.ArrayList;
import java.util.List;

public class HotelUserMapper extends AbstractMapper implements HotelUserMapperInterface {

    public HotelUserMapper(Connection con) {
        super(con);
    }

    /*
     * Returns a list of all hotelUsers in the database
     */
    @Override
    public List<HotelUser> getAllUsers() {
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
    public List<HotelUser> getUser(int id) {
        return search(id, "id");
    }

    @Override
    public List<HotelUser> search(Object variable, String columnName) {
        return executeQueryAndGatherResults(HotelUser.class,
                "SELECT * FROM hotel_user "
                + "WHERE " + columnName + " = ?",
                "Fail in HotelUserMapper - search ",
                new String[]{"id", "username", "psw", "status", "reservation_id", "spent"},
                new int[]{0, 1, 1, 1, 0, 0},
                variable);
    }

    @Override
    public boolean removeHotelUserByReservationID(int ID) {
        return 1 == executeSQLInsert(
                "DELETE FROM HOTEL_USER WHERE Reservation_id = ?",
                "Fail in HotelUserMapper - removeHotelUserByReservationID",
                ID);
    }
}
