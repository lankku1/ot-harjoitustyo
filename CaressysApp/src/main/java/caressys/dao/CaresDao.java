
package caressys.dao;

import caressys.domain.Cares;
import java.time.LocalDate;
import java.util.List;

public interface CaresDao {
    
    Cares create(Cares reservation) throws Exception;

    List<Cares> getAll() throws Exception;
    
    Cares findByArrivalDate(LocalDate date) throws Exception;
    
    boolean datesGivenOverlapsWithExisting(LocalDate from, LocalDate to) throws Exception;
    
    void deleteReservation(Cares r) throws Exception;
}
