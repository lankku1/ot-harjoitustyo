
package caressys.domain;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

public class UserTest {
    
    User user;
    
    @Before
    public void setUp() {
        user = new User("flamingo", "Pete");
    }

    @Test
    public void hello() {}
    
    @Test
    public void constructedUserExists() {
        assertTrue(user!=null);
    }
     
    @Test
    public void constructorSetsNameRight() {
        assertEquals("Pete", user.getName());
    }
    
    @Test
    public void constructorSetsUsernameRight() {
        System.out.println(user.getUsername());
        assertEquals("flamingo", user.getUsername());
    }
    
    @Test
    public void userDoesntEqualWithAnotherUser() {
        User u = new User("chipmunk", "Leo");
        assertFalse(user.equals(u));
    }
    
    @Test
    public void userEqualsWithAnotherUser() {
        User u = new User("flamingo", "Pete");
        assertTrue(user.equals(u));
    }
}
