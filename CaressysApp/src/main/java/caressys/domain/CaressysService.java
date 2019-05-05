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

    /**
    * loggin in by a given username.
    * Find the parameter username from userDao. 
    * @param username username given by the application user trying to log in
    * @return If given username doesn't exist, return false, otherwise return true.
     */
    
    public boolean login(String username) {
        User user = userDao.findByUsername(username);
        if (user == null) {
            return false;
        }
        loggedInUser = user;
        return true;
    }

    /**
    * log out from the app
     */
    
    public void logout() {
        loggedInUser = null;
    }

    /**
     * @return the user is logged in and using the application.
     */
    
    public User getLoggedInUser() {
        return loggedInUser;
    }

    /**
    * for creating a new user. 
    * @param username user has given out the parameter for username 
    * @param name user has given out the parameter for name
    * @return true, if a new user has been created succesfully, otherwise it will return false (for example, if
    * the username is already taken or there is an exception while 
    * creating a new user.
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

    /**
     * for creating a new reservation. 
     * @param arrival the user will pick a certain date for arrival of the reservation
     * @param departure the user will pick a certain date for departure of the reservation
     * @return true if the reservation has been created succesfully, otherwise it will return false (i.e., if the reservation overlaps an existing reservation).
     * 
     */
    public boolean createReservation(LocalDate arrival, LocalDate departure) throws Exception {
        /*
        first check if there already exists a reservation between the duration given.
        if the method won't find a reservation, return true, otherwise return false.
         */
        boolean status = caresDao.datesGivenOverlapsWithExisting(arrival, departure);
        if (status) {
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

    /**
     * used for listing all the reservation made
     * @return a list that contains all the existing reservations made
     */
    
    public List<Cares> listAllReservations() throws Exception {
        
        if (loggedInUser == null) {
            return new ArrayList<>();
        }

        return caresDao.getAll()
                .stream()
                .collect(Collectors.toList());
    }
    
    public boolean deleteWantedReservation(Cares res) throws Exception {
        if (caresDao.findByArrivalDate(res.getArrival()) == null) {
            return false;
        }
        caresDao.deleteReservation(res);
        return true;
    }
    
    /**
     * finds a specific reservation using the caresDao method findByArrivalDate
     * @param arrival arrivaldate for the specific reservation
     * @return a specific reservation found by the given date, if there doesn't exist a reservation for the given date, return null
     */
    public Cares getReservation(LocalDate arrival) throws Exception {
        return caresDao.findByArrivalDate(arrival);
    }
}
