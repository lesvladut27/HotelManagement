package ro.fortech.academy.project.hotel.persistence;

import ro.fortech.academy.project.hotel.business.entities.Clients;

import java.util.ArrayList;
import java.util.List;

public class DataAccessObjectSQL implements DataAccessObject {

    @Override
    public List<Clients> loadAllClients() throws DataAccesObjectException {
        System.out.println("loadAllPeople - dummy SQL implementation");

        return new ArrayList<>();
    }

    @Override
    public void saveAllClients(List<Clients> clients) {
        System.out.println("saveAllPeople - dummy SQL implementation");


    }
}
