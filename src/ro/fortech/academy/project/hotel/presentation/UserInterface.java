package ro.fortech.academy.project.hotel.presentation;

import ro.fortech.academy.project.hotel.business.entities.Clients;
import ro.fortech.academy.project.hotel.business.entities.InvalidRoomIdException;
import ro.fortech.academy.project.hotel.business.entities.InvalidSsnException;
import ro.fortech.academy.project.hotel.business.services.ClientsService;
import ro.fortech.academy.project.hotel.business.entities.HotelRooms;
import ro.fortech.academy.project.hotel.business.services.HotelRoomServiceException;
import ro.fortech.academy.project.hotel.business.services.HotelRoomsService;

import java.io.BufferedReader;
import java.io.Closeable;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.List;

public class UserInterface implements Closeable {

    private final BufferedReader bufferedReader;
    private final ClientsService clientsService;
    private final HotelRoomsService hotelRoomsService;

    public UserInterface(ClientsService clientsService, HotelRoomsService hotelRoomsService) {
        InputStreamReader inputStreamReader = new InputStreamReader(System.in);
        bufferedReader = new BufferedReader(inputStreamReader);
        this.clientsService = clientsService;
        this.hotelRoomsService = hotelRoomsService;
    }

    public void printGreetingMessage() {
        System.out.println("Hello from the Hotel Management dear client :D!");
    }

    private String readLine() {
        try {
            return bufferedReader.readLine();
        } catch (IOException exception) {
            throw new RuntimeException(exception);
        }
    }

    public void showMainMenu() {
        String option;
        do {
            printOptions();
            System.out.print("Please choose your option: ");
            option = readLine();
            System.out.println("Your option is: " + option);
            handleOption(option);
        } while (shouldContinue(option));
    }


    private void printOptions() {
        System.out.println("------------------------------------------");
        System.out.println("1 - Print whole clients data");
        System.out.println("2 - Search client by name");
        System.out.println("3 - Add new client");
        System.out.println("4 - Remove existing client");
        System.out.println();
        System.out.println("5 - Print whole hotel rooms data");
        System.out.println("6 - Add new hotel room");
        System.out.println("7 - Remove existing hotel room");
        System.out.println("0 - Exit");
        System.out.println("------------------------------------------");
    }

    private void handleOption(String option) {
        switch (option) {
            case "1": handlePrintClientData(); break;
            case "2": handleSearchClient(); break;
            case "3": handleAddNewClient(); break;
            case "4": handleRemoveExistingClient(); break;

            case "5": handlePrintHotelRoomsData(); break;
            case "6": handleAddNewHotelRoom(); break;
            case "7": handleRemoveExistingHotelRoom(); break;

            case "0": handleExitApplication(); break;
            default: handleInvalidOption(option);
        }
    }

    private void handleSearchClient() {
        System.out.println("Please enter client name:");
        String search = readLine();
        Clients foundClient = clientsService.searchClientName(search);
        if(foundClient != null) {
            System.out.println("Found following client: " + foundClient.toString());
        } else {
            System.out.println("Client name not found! :(");
        }
    }

    private void handleRemoveExistingHotelRoom() {
        System.out.println("Remove existing room...");
        try {
            System.out.println("Please enter room ID: ");
            String roomID = readLine();
            hotelRoomsService.removeExistingRoom(roomID);
        } catch (HotelRoomServiceException exception) {
            System.out.println(exception.getMessage());
        }
    }


    private void handleAddNewHotelRoom() {
        System.out.println("Add new hotel room...");
        try {
            HotelRooms newRoom = readHotelRoom();
            hotelRoomsService.addNewRoom(newRoom);
        } catch (HotelRoomServiceException exception) {
            System.out.println(exception.getMessage());
        } catch (InvalidRoomIdException exception) {
            System.out.println(exception.getRoomID() + " is not a valid Room ID.");
        }
    }

    private HotelRooms readHotelRoom() throws InvalidRoomIdException {
        System.out.println("Please enter room ID: ");
        String roomID = readLine();
        System.out.println("Please enter room type: ");
        String roomType = readLine();
        System.out.println("Please enter the status: ");
        String status = readLine();

        HotelRooms hotelRooms = new HotelRooms(roomID, roomType, status);
        return hotelRooms;
    }

    private void handlePrintHotelRoomsData() {
        System.out.println("Print hotel rooms data...");
        try {
            List<HotelRooms> hotelRooms = hotelRoomsService.getHotelRooms();
            printHotelRoomsData(hotelRooms);
        } catch (HotelRoomServiceException exception) {
            System.out.println(exception.getMessage());
        }
    }

    private void printHotelRoomsData(List<HotelRooms> hotelRooms) {
        for (HotelRooms currentHotelRoom : hotelRooms) {
            System.out.println(currentHotelRoom.toString());
        }
    }

    private void handlePrintClientData() {
        System.out.println("Print clients data...");
        List<Clients> clients = clientsService.getClients();
        printClientsData(clients);
    }

    private void printClientsData(List<Clients> clients) {
        for (Clients currentClient : clients) {
            System.out.println(currentClient.toString());
        }
    }

    private void handleAddNewClient() {
        System.out.println("Add new client...");
        try {
            Clients newClient = readClient();
            clientsService.addNewClient(newClient);
        } catch (InvalidSsnException e) {
            System.out.println(e.getSsn() + " is invalid.");
        }
    }

    private Clients readClient() throws InvalidSsnException {
        System.out.println("Please enter social security number: ");
        String ssn = readLine();
        System.out.println("Please enter first name: ");
        String firstName = readLine();
        System.out.println("Please enter last name: ");
        String lastName = readLine();
        System.out.println("Please enter budget: ");
        float budget = Float.parseFloat(readLine());

        Clients clients = new Clients(ssn, firstName, lastName, budget);
        return clients;
    }

    private void handleRemoveExistingClient() {
        System.out.println("Remove existing client...");
        System.out.println("Please enter social security number: ");
        String ssn = readLine();
        clientsService.removeExistingClient(ssn);
    }

    private void handleExitApplication() {
        System.out.println("Exit application...");
        System.out.println("Bye bye!");
    }

    private void handleInvalidOption(String option) {
        System.out.println(option + " is invalid");
    }

    private boolean shouldContinue(String option) {
        if ("0".equals(option)) {
            return false;
        } else {
            return true;
        }
    }


    @Override
    public void close() throws IOException {
        System.out.println("Bye Bye!");
    }
}









