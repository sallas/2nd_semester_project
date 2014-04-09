package dataSource;

import domain.HotelUser;
import java.sql.Connection;
import java.util.List;

public class HotelUserMapper extends AbstractMapper implements HotelUserMapperInterface {

    public HotelUserMapper(Connection con) {
        super(con, "hotel_user", HotelUser.class);
    }

    /*
     * Returns a list of all hotelUsers in the database
     */
    @Override
    public List<HotelUser> getAllUsers() {
        return executeQueryAndGatherResults(HotelUser.class,
                "SELECT * FROM hotel_user",
                "Fail in HotelUserMapper - getAllUsers");
    }

    /*
     * Returns one hoteluser
     */
    @Override
    public List<HotelUser> getUser(int id) {
        return generalSearch("id", "Fail in HotelUserMapper - getUser", id);
    }

    @Override
    public List<HotelUser> search(Object variable, String columnName) {
        return generalSearch(columnName,
                "Fail in HotelUserMapper - search ", variable);
    }

    @Override
    public boolean removeHotelUserByReservationID(int ID) {
        return 1 == executeSQLInsert(
                "DELETE FROM HOTEL_USER WHERE Reservation_id = ?",
                "Fail in HotelUserMapper - removeHotelUserByReservationID",
                ID);
    }
}
