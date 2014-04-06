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
    public List<Facility> getFacilities(String type) {
        return search(type, "type");
    }

    @Override
    public ArrayList<Facility> getFacilities() {
        ArrayList<Facility> facilities = executeQueryAndGatherResults(Facility.class,
                "SELECT * FROM facility",
                "Fail in FacilityMapper - getFacilities");
        return facilities;
    }

    @Override
    public List<Facility> getFacilityByID(int ID) {
        return search(ID, "id");
    }

    @Override
    public List<Facility> search(Object variable, String columnName) {
        ArrayList<Facility> facilities = executeQueryAndGatherResults(Facility.class,
                "SELECT * FROM facility "
                + "WHERE " + columnName + " = ?",
                "Fail in FacilityMapper - search ",
                variable);
        return facilities;
    }
}
