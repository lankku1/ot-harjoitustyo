
package caressys.domain;

import java.time.Duration;
import java.time.LocalDate;

/*
For a single reservation
*/

public class Cares { // Calendar Reservation
    private int id;
    private LocalDate arrivalDate;
    private LocalDate departureDate;
    private User user;
    
    public Cares(int id, LocalDate arrival, LocalDate departure, User user) {
        this.id = id;
        this.arrivalDate = arrival;
        this.departureDate = departure;
        this.user = user;
    }
    
    public void setId(int id) {
        this.id = id;
    }
    
    public LocalDate getArrival() {
        return this.arrivalDate;
    }
    
    public LocalDate getDeparture() {
        return this.departureDate;
    }
    
    public User getUser() {
        return user;
    }

    public int getId() {
        return id;
    }
    
    public Duration getDuration() {
        Duration duration = Duration.between(arrivalDate, departureDate);
        return duration;
    }
    
    @Override
    public boolean equals(Object obj) {
        if (!(obj instanceof Cares)) {
            return false;
        }
        Cares other = (Cares) obj;
        return id == other.id;
    }
}
