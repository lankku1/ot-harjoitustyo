
package caressys.dao;

import caressys.domain.User;
import java.io.File;
import java.io.FileWriter;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class FileUserDao implements UserDao {
    
    // in this class we are able to keep track of the users
    private List<User> users;
    private String file; // file consists of all the users we have

    public FileUserDao(String file) throws Exception {
        users = new ArrayList<>();
        this.file = file;
        
        try {
            Scanner reader = new Scanner(new File(file));
            while (reader.hasNextLine()) {
                String[] parts = reader.nextLine().split(";"); // splits the line between the name and the username
                User u = new User(parts[0], parts[1]);
                users.add(u);
            }
        } catch (Exception e) {
            FileWriter writer = new FileWriter(new File(file));
            writer.close();
        }
    }
    
    /**
     * save the user by using a filewriter object
     * @throws Exception 
     */
    private void save() throws Exception {
        
        try (FileWriter writer = new FileWriter(new File(file))) {
            for (User user : users) {
                writer.write(user.getUsername() + ";" + user.getName() + "\n");
            }
        } 
    }
    
    /**
     * lists all the users that are already in use
     * @return list of all the users
     */
    @Override
    public List<User> getAll() {
        return users;
    }
    
    /**
     * finds a specific user the application has in use
     * @param username 
     * @return username that equals with the parameter, if it doesn't exist, return null
     */
    @Override
    public User findByUsername(String username) {
        return users.stream()
            .filter(u->u.getUsername()
            .equals(username))
            .findFirst()
            .orElse(null);
    }
    
    /**
     * used for creating a new user
     * @param user user has been created in the CaresService class
     * @return the given user
     * @throws Exception 
     */
    @Override
    public User create(User user) throws Exception {
        users.add(user);
        save();
        return user;
    }
    
}
