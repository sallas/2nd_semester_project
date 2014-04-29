package dataSource;

import domain.Instructor;
import java.sql.Connection;
import java.util.List;

public class InstructorMapper extends AbstractMapper implements InstructorMapperInterface {

    public InstructorMapper(Connection con) {
        super(con, "instructor", Instructor.class);
    }

    @Override
    public List<Instructor> search(Object variable, String columnName) {
        return generalSearch(columnName, 
                "Fail in InstructorMapper - search ", variable);
    }
}
