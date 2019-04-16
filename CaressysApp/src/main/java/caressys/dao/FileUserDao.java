
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
    
    private void save() throws Exception {
        //save the user by using a filewriter object
        try (FileWriter writer = new FileWriter(new File(file))) {
            for (User user : users) {
                writer.write(user.getUsername() + ";" + user.getName() + "\n");
            }
        } 
    }
    
    @Override
    public List<User> getAll() {
        // lists all the users that are already in use
        return users;
    }
    
    @Override
    public User findByUsername(String username) {
        // finds a specific user we have in use
        return users.stream()
            .filter(u->u.getUsername()
            .equals(username))
            .findFirst()
            .orElse(null);
    }
    
    @Override
    public User create(User user) throws Exception {
        // used for creating a new user
        users.add(user);
        save();
        return user;
    }
    
}
