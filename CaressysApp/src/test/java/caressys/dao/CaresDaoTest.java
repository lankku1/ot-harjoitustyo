
package caressys.dao;

import caressys.domain.Cares;
import caressys.domain.FakeUserDao;
import java.io.File;
import java.io.FileWriter;
import java.time.LocalDate;
import java.time.Month;
import java.util.List;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;
import org.junit.Rule;
import org.junit.rules.TemporaryFolder;

public class CaresDaoTest {
    
    @Rule
    public TemporaryFolder testFolder = new TemporaryFolder();    
  
    File userFile;
    CaresDao caresDao;
    
    @Before
    public void setUp() throws Exception {
        userFile = testFolder.newFile("testfile_users.txt");  
        UserDao userDao = new FakeUserDao(); //user muumi created in the FakeUserDao class
        try (FileWriter file = new FileWriter(userFile.getAbsolutePath())) {
            file.write("1;2019-06-04;2019-06-07;muumi\n"
                    + "2;2019-07-02;2019-07-20;muumi\n"
                    + "3;2019-08-07;2019-08-10;muumi\n");
        }
        caresDao = new FileCaresDao(userFile.getAbsolutePath(), userDao);
    }
    
    @After
    public void tearDown() {
        userFile.delete();
    }
    
    @Test
    public void hello() {}
     
    @Test
    public void reservationsAreReadCorrectlyFromFile() throws Exception {
        List<Cares> reservations = caresDao.getAll();
        assertEquals(3, reservations.size());
        
        Cares res = reservations.get(0);
        
        assertEquals(1, res.getId());
        assertEquals(LocalDate.of(2019, Month.JUNE, 04), res.getArrival());
        assertEquals(LocalDate.of(2019, Month.JUNE, 07), res.getDeparture());
        assertEquals("muumi", res.getUser().getUsername());
    }
    
    @Test
    public void correctReservationFoundWithArrivalDate() throws Exception {
        LocalDate date = LocalDate.of(2019, Month.JULY, 02);
        Cares res = caresDao.findByArrivalDate(date);
        
        assertEquals(2, res.getId());
        assertEquals(LocalDate.of(2019, Month.JULY, 02), res.getArrival());
        assertEquals(LocalDate.of(2019, Month.JULY, 20), res.getDeparture());
        assertEquals("muumi", res.getUser().getUsername());
    }
    
    @Test
    public void returnsNullWhenFindingWithWrongDate() throws Exception {
        LocalDate date = LocalDate.of(2019, Month.JUNE, 01);
        Cares res = caresDao.findByArrivalDate(date);
        
        assertEquals(null, res);
    }
    
    @Test
    public void newReservationIsNotOverlappingWithOthers() throws Exception {
        LocalDate from = LocalDate.of(2019, Month.JUNE, 10);
        LocalDate to = LocalDate.of(2019, Month.JUNE, 15);
        
        boolean status = caresDao.datesGivenOverlapsWithExisting(from, to);
        
        assertFalse(status);
    }
    
    @Test
    public void newReservationOverlapsWithExistingReservation1() throws Exception{
        LocalDate from = LocalDate.of(2019, Month.JUNE, 6);
        LocalDate to = LocalDate.of(2019, Month.JUNE, 12);
        
        boolean status = caresDao.datesGivenOverlapsWithExisting(from, to);
        
        assertTrue(status);
    }
    
    @Test
    public void newReservationOverlapsWithExistingReservation2() throws Exception {
        LocalDate from = LocalDate.of(2019, Month.JULY, 6);
        LocalDate to = LocalDate.of(2019, Month.JULY, 12);
        
        boolean status = caresDao.datesGivenOverlapsWithExisting(from, to);
        
        assertTrue(status);
    }
    
    @Test
    public void newReservationOverlapsWithExistingReservation3() throws Exception {
        LocalDate from = LocalDate.of(2019, Month.AUGUST, 6);
        LocalDate to = LocalDate.of(2019, Month.AUGUST, 12);
        
        boolean status = caresDao.datesGivenOverlapsWithExisting(from, to);
        
        assertTrue(status);
    }
    
    @Test
    public void arrivaldateIsTheSameAsExistingDepartureReturnsFalse() throws Exception {
        LocalDate from = LocalDate.of(2019, Month.JUNE, 20);
        LocalDate to = LocalDate.of(2019, Month.JUNE, 23);
        
        boolean status = caresDao.datesGivenOverlapsWithExisting(from, to);
        
        assertFalse(status);
    }
    
    @Test
    public void departuredateIsTheSameAsExistingArrivalReturnsFalse() throws Exception {
        LocalDate from = LocalDate.of(2019, Month.JUNE, 01);
        LocalDate to = LocalDate.of(2019, Month.JUNE, 04);
        
        boolean status = caresDao.datesGivenOverlapsWithExisting(from, to);
        
        assertFalse(status);
    }
    
    @Test
    public void reservationWithTheSameDatesReturnsTrue() throws Exception {
        LocalDate from = LocalDate.of(2019, Month.JUNE, 04);
        LocalDate to = LocalDate.of(2019, Month.JUNE, 07);
        
        boolean status = caresDao.datesGivenOverlapsWithExisting(from, to);
        
        assertTrue(status);
    }
    
    @Test
    public void reservationDeletedSuccesfully() throws Exception {
        
    }
    
}
