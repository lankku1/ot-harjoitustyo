
package caressys.domain;

import java.time.LocalDate;
import java.time.Month;
import java.util.List;
import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;

public class CaressysServiceCaresTest {
    
    FakeUserDao userDao;
    FakeCaresDao caresDao;
    CaressysService service;
    
    @Before
    public void setUp() throws Exception {
        userDao = new FakeUserDao();
        caresDao = new FakeCaresDao();
        
        User u1 = new User("testeri1", "Maisa");
        User u2 = new User("testeri1", "Mikko");
        userDao.create(u1);
        userDao.create(u2);
        caresDao.create(new Cares(1, LocalDate.of(2019, Month.JUNE, 06),LocalDate.of(2019, Month.JUNE, 12), u1));
        caresDao.create(new Cares(1, LocalDate.of(2019, Month.JUNE, 15),LocalDate.of(2019, Month.JUNE, 20), u2));
        service = new CaressysService(userDao, caresDao);
        service.login("testeri1");
    }
    
     @Test
     public void hello() {}
     
    @Test
    public void reservationsCreatedCorrectly() throws Exception {
        List<Cares> reservations = caresDao.getAll();
        Cares res = reservations.get(0);
        assertEquals(2, reservations.size());
        assertEquals(1, res.getId());
        assertEquals(LocalDate.of(2019, Month.JUNE, 06), res.getArrival());
        assertEquals(LocalDate.of(2019, Month.JUNE, 12), res.getDeparture());
        assertEquals("testeri1", res.getUser().getUsername());
    }
    
    
    @Test
    public void newReservationAddedSuccesfully() throws Exception {
        boolean status = service.createReservation(LocalDate.of(2019, Month.JUNE, 20), LocalDate.of(2019, Month.JUNE, 26));
        assertTrue(status);
    }
     
    @Test
    public void creatingNewReservationFailsWithoutLoggedInUser() throws Exception {
        service.logout();
        boolean status = service.createReservation(LocalDate.of(2019, Month.JUNE, 15), LocalDate.of(2019, Month.JUNE, 16));
        assertFalse(status);
    }
    
    @Test
    public void reservationDeletedSuccesfully() throws Exception {
        Cares res = service.getReservation(LocalDate.of(2019, Month.JUNE, 15));
        boolean status = service.deleteWantedReservation(res);
        
        assertTrue(status);
    }
    
    @Test
    public void deletingNotExistingReservationFails() throws Exception {
        Cares res = new Cares(0, LocalDate.of(2019, Month.JUNE, 23), LocalDate.of(2019, Month.JUNE, 27), null);
        boolean status = service.deleteWantedReservation(res);
        
        assertFalse(status);
    }
    
}
