package ro.fortech.academy.project.hotel.persistence;

import ro.fortech.academy.project.hotel.business.entities.Clients;
import ro.fortech.academy.project.hotel.business.entities.HotelRooms;
import ro.fortech.academy.project.hotel.business.entities.InvalidRoomIdException;
import ro.fortech.academy.project.hotel.business.entities.InvalidSsnException;
import ro.fortech.academy.project.hotel.utils.Constants;

import javax.xml.crypto.Data;
import java.io.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class DataAccesObjectTXT implements DataAccessObject {

    @Override
    public List<Clients> loadAllClients() {
        List<Clients> people = new ArrayList<>();

        try (FileReader fileReader = new FileReader(Constants.clientsPath)) {
            BufferedReader bufferedReader = new BufferedReader(fileReader);
            String currentLine = bufferedReader.readLine();

            while (!Constants.fileEndSeparator.equals(currentLine)) {
                try {
                    Clients currentPerson = readClient(bufferedReader);
                    people.add(currentPerson);
                } catch (InvalidSsnException exception) {

                }
                currentLine = bufferedReader.readLine();
            }
        } catch (IOException exception) {
            throw new RuntimeException(exception);
        }
        return people;
    }

    public List<HotelRooms> loadAllRooms() throws DataAccesObjectException {
        List<HotelRooms> hotelRooms = new ArrayList<>();

        try (FileReader fileReader = new FileReader(Constants.hotelRoomsPath)) {
            BufferedReader bufferedReader = new BufferedReader(fileReader);
            String currentLine = bufferedReader.readLine();

            while (!Constants.fileEndSeparator.equals(currentLine)) {
                try {
                    HotelRooms currentRoom = readHotelRooms(bufferedReader);
                    hotelRooms.add(currentRoom);
                } catch (InvalidRoomIdException exception) {

                }
                currentLine = bufferedReader.readLine();
            }
        } catch (IOException exception) {
            throw new DataAccesObjectException("Some sort of exception!", LocalDateTime.now());
        }
        return hotelRooms;
    }

    private Clients readClient(BufferedReader bufferedReader) throws IOException, InvalidSsnException {
        String ssn = bufferedReader.readLine();
        String firstName = bufferedReader.readLine();
        String lastName = bufferedReader.readLine();
        float budget = Float.parseFloat(bufferedReader.readLine());

        return new Clients(ssn, firstName, lastName, budget);
    }

    private HotelRooms readHotelRooms(BufferedReader bufferedReader) throws IOException, InvalidRoomIdException {
        String roomID = bufferedReader.readLine();
        String roomType = bufferedReader.readLine();
        String status = bufferedReader.readLine();
        float price = Float.parseFloat(bufferedReader.readLine());

        return new HotelRooms(roomID, roomType, status, price);
    }

    @Override
    public void saveAllClients(List<Clients> clients) {
        try (FileWriter fileWriter = new FileWriter(Constants.clientsPath)) {
            PrintWriter printWriter = new PrintWriter(fileWriter);

            for(Clients currentClient : clients) {
                writeClient(printWriter, currentClient);
            }
            printWriter.println(Constants.fileEndSeparator);
        } catch (IOException exception) {
            throw new RuntimeException(exception);
        }
    }

    public void saveAllRooms(List<HotelRooms> hotelRooms) {
        try (FileWriter fileWriter = new FileWriter(Constants.hotelRoomsPath)) {
            PrintWriter printWriter = new PrintWriter(fileWriter);

            for(HotelRooms currentRoom : hotelRooms) {
                writeRoom(printWriter, currentRoom);
            }
            printWriter.println(Constants.fileEndSeparator);
        } catch (IOException exception) {
            throw new RuntimeException(exception);
        }
    }

    private void writeClient(PrintWriter printWriter, Clients clients) {
        printWriter.println(Constants.entitiesSeparator);

        printWriter.println(clients.getSsn());
        printWriter.println(clients.getFirstName());
        printWriter.println(clients.getLastName());
        printWriter.println(clients.getBudget());
    }

    private void writeRoom(PrintWriter printWriter, HotelRooms hotelRooms) {
        printWriter.println(Constants.entitiesSeparator);

        printWriter.println(hotelRooms.getRoomID());
        printWriter.println(hotelRooms.getRoomType());
        printWriter.println(hotelRooms.getStatus());
        printWriter.println(hotelRooms.getPrice());
    }

}


