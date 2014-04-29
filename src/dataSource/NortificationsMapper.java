package dataSource;

import java.sql.Connection;
import domain.Nortification;
import java.util.List;

public class NortificationsMapper extends  AbstractMapper implements NortificationsMapperInterface{

    public NortificationsMapper(Connection con) {
        super(con, "nortifications", Nortification.class);
    }
    
    @Override
    public List<Nortification> getAllNortifications() {
        return executeQueryAndGatherResults(Nortification.class,
                "SELECT * FROM nortifications",
                "Fail in NortificationsMapper - getAllNortifications");
    }
    
    @Override
    public boolean saveNortification(Nortification n) {
        int seqNum = getSequenceNumber("SELECT nortificationsseq.nextval"
                + " FROM dual",
                "Fail in NortificationMapper - saveNortification");
        n.setID(seqNum);
        int result = executeSQLInsert(
                "INSERT INTO nortifications VALUES (?, ?)",
                "Fail in NortificationMapper - saveNortification",
                n.getID(), n.getMessage());
        return result != 0;
    }
    
    @Override
    public boolean deleteAllNortifications() {
        int result = executeSQLInsert(
                "DELETE FROM nortifications",
                "Fail in NortificationMapper - deleteAllNortifications");
        return result != 0;
    }
}
