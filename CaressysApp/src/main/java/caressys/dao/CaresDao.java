
package caressys.dao;

import caressys.domain.Cares;
import java.time.LocalDate;
import java.util.List;

public interface CaresDao {
    
    Cares create(Cares reservation) throws Exception;

    List<Cares> getAll();
    
    LocalDate findByArrivalDate(LocalDate date);
    
    LocalDate findByDepartureDate(LocalDate date);
    
    boolean datesGivenOverlapsWithExisting(LocalDate from, LocalDate to);
}
