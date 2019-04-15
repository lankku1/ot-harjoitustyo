
package caressys.dao;

import caressys.domain.Cares;
import java.time.LocalDate;
import java.util.List;

public interface CaresDao {
    
    Cares create(Cares res) throws Exception;

    List<Cares> getAll();
    
    LocalDate findByDate(LocalDate date);
}
