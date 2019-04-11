
package caressys.domain;

import caressys.dao.UserDao;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

public class CaressysServiceUserTest {
    
    FakeUserDao userDao;
    CaressysService service;
    
    @Before
    public void setUp() {
        userDao = new FakeUserDao();
        service = new CaressysService(userDao);
    }

     @Test
     public void hello() {}
     
    @Test
    public void nonexistentUserIsNotLoggedIn() {
        boolean status = service.login("notauser");
        assertFalse(status);
        
        assertEquals(null, service.getLoggedInUser());
    }
    
    @Test
    public void existingUserIsLoggedIn() {
        boolean status = service.login("muumi");
        assertTrue(status);
        
        User logged = service.getLoggedInUser();
        assertEquals("Maija Meikäläinen", logged.getName());
    }
    
    @Test
    public void loggedInUserLogsOut() {
        service.login("muumi");
        service.logout();
        assertEquals(null, service.getLoggedInUser());
    }
    
    @Test
    public void newUserCreatedSuccesfullyAndCanLogIn() throws Exception {
        boolean result = service.createUser("flamingo", "Pete");
        assertTrue(result);
        
        boolean loginStatus = service.login("flamingo");
        assertTrue(loginStatus);
        
        User logged = service.getLoggedInUser();
        assertEquals("Pete", logged.getName());
    }
    
    @Test
    public void createNewUserWithTakenUsername() throws Exception {
        boolean result = service.createUser("muumi", "Matti Meikäläinen");
        assertFalse(result);
    }
}
