
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
    public List<Cares> getAll() {
        return reservations;
    }

    @Override
    public LocalDate findByArrivalDate(LocalDate date) {
        return reservations.stream()
                .filter(r -> r.getArrival()
                .equals(date))
                .findFirst()
                .orElse(null)
                .getArrival();
    }

    @Override
    public LocalDate findByDepartureDate(LocalDate date) {
        return reservations.stream()
                .filter(r -> r.getDeparture()
                .equals(date))
                .findFirst()
                .orElse(null)
                .getDeparture();
    }

    
}
