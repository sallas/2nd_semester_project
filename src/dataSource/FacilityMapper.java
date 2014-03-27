package dataSource;

import domain.Facility;
import domain.Room;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

public class FacilityMapper extends AbstractMapper implements FacilityMapperInterface {

    public FacilityMapper(Connection con) {
        super(con);
    }
    
    /*
     * Get facilities by type.
     */
    @Override
    public ArrayList<Facility> getFacilities(String type) {
        ArrayList<Facility> facilities = null;
        ResultSet rs = executeSQLQuery("SELECT * FROM facility",
                "Fail in FacilityMapper - getFacilities", type);
        try {
            while (rs.next()) {
                    facilities.add(new Facility(rs.getInt(1),
                            rs.getString(2), 
                            rs.getString(3),
                            rs.getInt(4),
                            rs.getBoolean(5),
                            rs.getBoolean(6), 
                            rs.getBoolean(7)));
                }
            rs.close();
        } catch (SQLException ex) {
            System.out.println("Fail in FaciltyMapper - getFacilities");
            System.out.println(ex.getMessage());
        }
        return facilities;
    }
    
}
