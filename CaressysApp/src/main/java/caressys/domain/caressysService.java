
package caressys.domain;

public class caressysService {
    
    
    public caressysService() {
        
    }
    
    public void createUser(String username, String name) {
        User newUser = new User(name, username);
    }
}
