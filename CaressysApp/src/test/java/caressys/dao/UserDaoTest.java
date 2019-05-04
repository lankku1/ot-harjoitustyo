
package caressys.dao;

import caressys.domain.User;
import java.io.File;
import java.io.FileWriter;
import java.util.List;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;
import org.junit.Rule;
import org.junit.rules.TemporaryFolder;

public class UserDaoTest {
    @Rule
    public TemporaryFolder testFolder = new TemporaryFolder();
    
    File userFile;  
    UserDao userDao;
    
    @Before
    public void setUp() throws Exception {
        userFile = testFolder.newFile("testfile_users.txt");  
        
        try (FileWriter file = new FileWriter(userFile.getAbsolutePath())) {
            file.write("muumi;Maija Meikäläinen\n");
        }
        
        userDao = new FileUserDao(userFile.getAbsolutePath());
    }
    
    @After
    public void tearDown() {
        userFile.delete();
    }

    @Test
    public void hello() {}
    
    @Test
    public void userIsReadCorrectlyFromFile() {
        List<User> users = userDao.getAll();
        assertEquals(1, users.size());
        
        User u = users.get(0);
        assertEquals("muumi", u.getUsername());
        assertEquals("Maija Meikäläinen", u.getName());
    }
    
    @Test
    public void newUserCreatedAndFoundSuccesfully() throws Exception {
        userDao.create(new User("nipsu", "Niilo Niittynen"));
        List<User> users = userDao.getAll();
        assertEquals(2, users.size());
        
        User u = users.get(1);
        assertEquals("nipsu", u.getUsername());
        assertEquals("Niilo Niittynen", u.getName());
        
        User u1 = userDao.findByUsername("nipsu");
        assertEquals("nipsu", u1.getUsername());
        assertEquals("Niilo Niittynen", u1.getName());
    }
    
    @Test
    public void findByUsernameWhitExistingUser() throws Exception {
        User u = userDao.findByUsername("muumi");
        assertEquals("muumi", u.getUsername());
        assertEquals("Maija Meikäläinen", u.getName());
    }
    
    @Test
    public void returnNullIfUserNotFound() {
        User u = userDao.findByUsername("hemuli");
        assertEquals(null, u);
    }
    
    
    
    
}
