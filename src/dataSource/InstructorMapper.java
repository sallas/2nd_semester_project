package dataSource;

import domain.Instructor;
import java.sql.Connection;
import java.util.List;

public class InstructorMapper extends AbstractMapper {

    public InstructorMapper(Connection con) {
        super(con);
    }

    public List<Instructor> search(Object variable, String columnName) {
        return executeQueryAndGatherResults(
                Instructor.class,
                "SELECT * FROM Instructor "
                + "WHERE " + columnName + " = ?",
                "Fail in InstructorMapper - search ",
                new String[]{"ID", "userID"},
                new int[]{DataType.INT, DataType.INT},
                variable);
    }
}
