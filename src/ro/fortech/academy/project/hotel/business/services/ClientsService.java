package ro.fortech.academy.project.hotel.business.services;

import ro.fortech.academy.project.hotel.business.entities.Clients;
import ro.fortech.academy.project.hotel.persistence.DataAccesObjectTXT;

import java.util.List;

public class ClientsService {

    private final DataAccesObjectTXT dataAccessObject;

    public ClientsService(DataAccesObjectTXT dataAccessObject) {
        this.dataAccessObject = dataAccessObject;
    }

    public List<Clients> getClients() {
        List<Clients> clients = dataAccessObject.loadAllClients();
        return clients;
    }

    public Clients searchClientName(String search) {
        List<Clients> clients = dataAccessObject.loadAllClients();
        for(Clients currentClient : clients) {
            if(currentClient.getFirstName().equals(search) || currentClient.getLastName().equals(search)) {
              return currentClient;
            }
        }
        return null;
    }

    public void addNewClient(Clients newClient) {
        List<Clients> clients = dataAccessObject.loadAllClients();
        clients.add(newClient);
        dataAccessObject.saveAllClients(clients);
    }

    public void removeExistingClient(String ssn) {
        List<Clients> clients = dataAccessObject.loadAllClients();
        int index = getClientIndex(clients, ssn);
        if (index != -1) {
            clients.remove(index);
            dataAccessObject.saveAllClients(clients);
        }
    }

    private int getClientIndex(List<Clients> clients, String ssn) {
        for (int i = 0; i < clients.size(); i++) {
            Clients currentClient = clients.get(i); // ??
            if (currentClient.getSsn().equals(ssn)) {
                return i;
            }
        }
        return -1;
    }

}

