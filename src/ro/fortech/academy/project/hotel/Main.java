package ro.fortech.academy.project.hotel;

import ro.fortech.academy.project.hotel.business.services.ClientsService;
import ro.fortech.academy.project.hotel.business.services.HotelRoomsService;
import ro.fortech.academy.project.hotel.persistence.DataAccesObjectTXT;
import ro.fortech.academy.project.hotel.persistence.DataAccessObject;
import ro.fortech.academy.project.hotel.persistence.DataAccessObjectSQL;
import ro.fortech.academy.project.hotel.presentation.UserInterface;

import java.io.IOException;

public class Main {

    public static void main(String[] args) throws IOException {

        DataAccesObjectTXT dataAccessObject = new DataAccesObjectTXT();
        ClientsService clientsService = new ClientsService(dataAccessObject);
        HotelRoomsService hotelRoomsService = new HotelRoomsService(dataAccessObject);
        UserInterface userInterface = new UserInterface(clientsService, hotelRoomsService);

        try (userInterface) {
            userInterface.printGreetingMessage();
            userInterface.showMainMenu();
        }
    }
}
