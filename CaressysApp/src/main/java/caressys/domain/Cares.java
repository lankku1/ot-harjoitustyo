
package caressys.domain;

import java.util.Date;

/*
For a single reservation
*/

public class Cares { // Calendar Reservation
    private int id;
    private Date arrivalDate;
    private Date departureDate;
    private User user;
    
    public Cares(int id, Date arrival, Date departure, User user) {
        this.id = id;
        this.arrivalDate = arrival;
        this.departureDate = departure;
        this.user = user;
    }
    
    public void setId(int id) {
        this.id = id;
    }
    
    public Date getArrival() {
        return this.arrivalDate;
    }
    
    public Date getDeparture() {
        return this.departureDate;
    }
    
    public User getUser() {
        return user;
    }

    public int getId() {
        return id;
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
