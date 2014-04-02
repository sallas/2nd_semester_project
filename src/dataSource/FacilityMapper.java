package dataSource;

import domain.Facility;
import java.sql.Connection;
import java.util.ArrayList;
import java.util.List;

public class FacilityMapper extends AbstractMapper implements FacilityMapperInterface {

    public FacilityMapper(Connection con) {
        super(con);
    }

    /*
     * Get facilities by type.
     */
    @Override
    public ArrayList<Facility> getFacilities(String type) {
        ArrayList<Facility> facilities = executeQueryAndGatherResults(Facility.class,
                "SELECT * FROM facility WHERE type = ?",
                "Fail in FacilityMapper - getFacilities",
                new String[]{"ID", "name", "type", "capacity",
                    "hasWaitingList", "hasBooking", "hasInstructor"},
                new int[]{0, 1, 1, 0, 3, 3, 3},
                type);
        return facilities;
    }

    @Override
    public ArrayList<Facility> getFacilities() {
        ArrayList<Facility> facilities = executeQueryAndGatherResults(Facility.class,
                "SELECT * FROM facility",
                "Fail in FacilityMapper - getFacilities",
                new String[]{"ID", "name", "type", "capacity",
                    "hasWaitingList", "hasBooking", "hasInstructor"},
                new int[]{0, 1, 1, 0, 3, 3, 3});
        return facilities;
    }

    @Override
    public List<Facility> getFacilityByID(int ID) {
        ArrayList<Facility> facilities = executeQueryAndGatherResults(Facility.class,
                "SELECT * FROM facility WHERE ID = ?",
                "Fail in FacilityMapper - getFacilities",
                new String[]{"ID", "name", "type", "capacity",
                    "hasWaitingList", "hasBooking", "hasInstructor"},
                new int[]{0, 1, 1, 0, 3, 3, 3}, ID);
        return facilities;
    }
}
