package dataSource;

import domain.Instructor;
import java.sql.Connection;
import java.util.List;

public class InstructorMapper extends AbstractMapper {

    public InstructorMapper(Connection con) {
        super(con, "instructor", Instructor.class);
    }

    public List<Instructor> search(Object variable, String columnName) {
        return generalSearch(columnName, 
                "Fail in InstructorMapper - search ", variable);
    }
}
