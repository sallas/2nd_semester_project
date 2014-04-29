package dataSource;

import domain.Instructor;
import java.util.List;

public interface InstructorMapperInterface {

    List<Instructor> search(Object variable, String columnName);
    
}
