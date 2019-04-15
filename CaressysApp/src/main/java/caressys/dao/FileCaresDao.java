
package caressys.dao;

import caressys.domain.Cares;
import java.time.LocalDate;
import java.util.List;

public class FileCaresDao implements CaresDao {
    // in this class we are able to keep track of all of the reservations made
    private List<Cares> reservations;
    private String file; // file consists of all the reservations created
    
    public FileCaresDao(String file)throws Exception {
        reservations = new ArrayList<>();
        
    }

    @Override
    public Cares create(Cares res) throws Exception {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public List<Cares> getAll() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public LocalDate findByDate(LocalDate date) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
}
