package dataSource;

import domain.Nortification;
import java.util.List;

public interface NortificationsMapperInterface {

    boolean deleteAllNortifications();

    List<Nortification> getAllNortifications();

    boolean saveNortification(Nortification n);
    
}
