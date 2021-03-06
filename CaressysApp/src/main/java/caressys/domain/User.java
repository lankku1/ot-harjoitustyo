
package caressys.domain;

public class User {
    private String name;
    private String username;
    
    /**
     * This class has the given information about a single user
     * @param username
     * @param name 
     */
    public User(String username, String name) {
        this.name = name;
        this.username = username;
    }
    
    public String getName() {
        return name;
    }

    public String getUsername() {
        return username;
    }
    
    @Override
    public boolean equals(Object obj) {
        if (!(obj instanceof User)) {
            return false;
        }
        
        User other = (User) obj;
        return username.equals(other.username);
    }
    
    public String toString() {
        return "name: " + this.name + ", username: " + this.username;
    }
}
