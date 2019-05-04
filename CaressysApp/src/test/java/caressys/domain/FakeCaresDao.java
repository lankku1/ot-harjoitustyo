
package caressys.domain;

import caressys.dao.CaresDao;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class FakeCaresDao implements CaresDao { 
// for test use, so testing won't implement with the actual reservation file
    ArrayList<Cares> reservations = new ArrayList<>();

    @Override
    public Cares create(Cares reservation) throws Exception {
        reservation.setId(reservations.size()+1);
        reservations.add(reservation);
        return reservation;
    }

    @Override
    public List<Cares> getAll() throws Exception {
        return reservations;
    }

    @Override
    public Cares findByArrivalDate(LocalDate date) throws Exception {
        return reservations.stream()
                .filter(r -> r.getArrival()
                .equals(date))
                .findFirst()
                .orElse(null);
    }

    @Override
    public boolean datesGivenOverlapsWithExisting(LocalDate from, LocalDate to) throws Exception {
        if (reservations.isEmpty()) {
            return true;
        }
        
        for (Cares reservation : reservations) {
            LocalDate a = reservation.getArrival();
            LocalDate d = reservation.getDeparture();
            
            if (from.equals(a) || to.equals(d)) {
                return true;
            } else if (from.isAfter(a) && (from.isBefore(d) || to.isBefore(d))) {
                return true;
            } else if (from.isBefore(a) && to.isAfter(a)) {
                return true;
            }
        }
        return false;
    }
}
