package dataSource;

import java.sql.Connection;
import domain.Nortification;
import java.util.List;

public class NortificationsMapper extends  AbstractMapper{

    public NortificationsMapper(Connection con) {
        super(con, "nortifications", Nortification.class);
    }
    
    public List<Nortification> getAllNortifications() {
        return executeQueryAndGatherResults(Nortification.class,
                "SELECT * FROM nortifications",
                "Fail in NortificationsMapper - getAllNortifications");
    }
}
