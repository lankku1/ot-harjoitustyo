package caressys.domain;

import caressys.dao.CaresDao;
import caressys.dao.UserDao;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class CaressysService {

    private UserDao userDao;
    private CaresDao caresDao;
    private User loggedInUser;

    public CaressysService(UserDao userDao, CaresDao caresDao) {
        this.userDao = userDao;
        this.caresDao = caresDao;
    }

    /*
    loggin in by a given username. 
    Find the parameter username from userDao. If it doesn't
    exist, return false, otherwise return true (username exists and
    is able to log in the app).
     */
    public boolean login(String username) {
        User user = userDao.findByUsername(username);
        if (user == null) {
            return false;
        }
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
        if (userDao.findByUsername(username) != null) {
            return false;
        }

        User user = new User(username, name);
        try {
            userDao.create(user);
        } catch (Exception e) {
            return false;
        }
        return true;
    }

    /*
    for creating a new reservation. Returns true if the reservation was
    created succesfully, otherwise it will return false (i.e., if the reservation overlaps an existing reservation).
     */
    public boolean createReservation(LocalDate arrival, LocalDate departure) {
        /*
        first check if there already exists a reservation between the duration given.
        if the method won't find a reservation, return true, otherwise return false.
         */
        boolean status = caresDao.datesGivenOverlapsWithExisting(arrival, departure);
        System.out.println("status " + status);
        if (!status) {
            return false;
        }
        
        Cares reservation = new Cares(0, arrival, departure, loggedInUser);
        try {
            caresDao.create(reservation);
        } catch (Exception e) {
            return false;
        }
        return true;
    }

    /*
    used for listing all the reservation made (for a specific user)
     */
    public List<Cares> listAllReservations() {
        
        if (loggedInUser == null) {
            return new ArrayList<>();
        }

        return caresDao.getAll()
                .stream()
                //.filter(t -> t.getArrival().isAfter(LocalDate.now())) // filtering all the previous reservations that aren't relevant anymore
                //.filter(t -> t.getUser().equals(loggedInUser)) not relevant anymore
                .collect(Collectors.toList());
    }
}
