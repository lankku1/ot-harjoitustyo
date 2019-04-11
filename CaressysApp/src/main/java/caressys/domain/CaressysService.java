
package caressys.domain;

import caressys.dao.UserDao;

public class caressysService {
    private UserDao userDao;
    private User loggedInUser;
    
    public caressysService(UserDao userDao) {
        this.userDao = userDao;
    }
    
    /*
    loggin in by a given username. 
    Find the parameter username from userDao. If it doesn't
    exist, return false, otherwise return true (username exists and
    is able to log in the app).
    */
    public boolean login(String username) {
        User user = userDao.findByUsername(username);
        if (user == null) return false;
        loggedInUser = user;
        return true;
    }
    
    /*
    log out from the app
    */
    
    public void logout() {
        loggedInUser = null;
    }
    
    /*
    returns the user that is logged in.
    */
    
    public User getLoggedInUser() {
        return loggedInUser;
    }
    
    /*
    for creating a new user. 
    The method will return true, if a new user has been created
    succesfully, otherwise it will return false (for example, if
    the username is already taken or there is an exception while 
    creating a new user.
    */
    public boolean createUser(String username, String name) {
        if (userDao.findByUsername(username) != null) return false;
        
        User user = new User(username, name);
        try {
            userDao.create(user);
        } catch(Exception e) {
            return false;
        }
        return true;
    }
}
