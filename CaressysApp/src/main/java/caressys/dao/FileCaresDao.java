
package caressys.dao;

import caressys.domain.Cares;
import caressys.domain.User;
import java.io.File;
import java.io.FileWriter;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class FileCaresDao implements CaresDao {
    // in this class we are able to keep track of all of the reservations made
    private List<Cares> reservations;
    private String file; // file consists of all the reservations created
    
    public FileCaresDao(String file, UserDao users)throws Exception {
        reservations = new ArrayList<>();
        this.file = file;
        try {
            Scanner reader = new Scanner(new File(file));
            while (reader.hasNextLine()) {
                String[] parts = reader.nextLine().split(";");
                int id = Integer.parseInt(parts[0]);
                User user = users.getAll().stream().filter(u->u.getUsername().equals(parts[3])).findFirst().orElse(null); 
                LocalDate arrival = LocalDate.parse(parts[1]);
                LocalDate departure = LocalDate.parse(parts[2]);
                Cares cares = new Cares(id, arrival, departure, user);
                reservations.add(cares);
            }
        } catch (Exception e) {
            FileWriter writer = new FileWriter(new File(file));
            writer.close();
        }
    }
    
    private void save() throws Exception {
        try (FileWriter writer = new FileWriter(new File(file))) {
            for (Cares reservation : reservations) {
                writer.write(reservation.getId() + ";" + reservation.getArrival() + ";" + reservation.getDeparture() + ";" + reservation.getUser().getUsername() + "\n");
            }
        }
    }
    
    private int generateId() {
        return reservations.size() + 1;
    }

    @Override
    public Cares create(Cares reservation) throws Exception {
        reservation.setId(generateId());
        reservations.add(reservation);
        save();
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
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
}