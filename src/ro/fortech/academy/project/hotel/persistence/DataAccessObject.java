package ro.fortech.academy.project.hotel.persistence;

import ro.fortech.academy.project.hotel.business.entities.Clients;

import java.util.List;

public interface DataAccessObject {

    List<Clients> loadAllClients() throws DataAccesObjectException;

    void saveAllClients(List<Clients> clients);
}
