
package caressys.domain;

import java.time.LocalDate;
import java.time.Month;
import static java.util.Calendar.MONTH;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

public class CaresTest {
    
//    @Before
//    public void setUp() {
//        LocalDate arrival = LocalDate.of(2019, Month.APRIL, 19);
//        LocalDate departure = LocalDate.of(2019, Month.APRIL, 23);
//    }
    
     @Test
     public void hello() {}
     
     @Test
     public void equalWhenSameId() {
         
         Cares reservation1 = new Cares(1, null, null, null);
         Cares reservation2 = new Cares(1, null, null, null);
         
         assertTrue(reservation1.equals(reservation2));
     }
     
     @Test
     public void notEqualWhenDifferentId() {
         Cares reservation1 = new Cares(1, null, null, null);
         Cares reservation2 = new Cares(2, null, null, null);
         
         assertFalse(reservation1.equals(reservation2));
     }
     
     @Test
     public void nonEqualDifferentType() {
         Cares reservation = new Cares(1, null, null, null);
         Object o = new Object();
         
         assertFalse(reservation.equals(o));
     }
}
