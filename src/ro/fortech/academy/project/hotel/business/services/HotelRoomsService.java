package ro.fortech.academy.project.hotel.business.services;

import ro.fortech.academy.project.hotel.business.entities.HotelRooms;
import ro.fortech.academy.project.hotel.persistence.DataAccesObjectTXT;
import ro.fortech.academy.project.hotel.persistence.DataAccesObjectException;

import java.util.List;

public class HotelRoomsService {
    private final DataAccesObjectTXT dataAccesObject;

    public HotelRoomsService(DataAccesObjectTXT dataAccesObject) {
        this.dataAccesObject = dataAccesObject;
    }

    public List<HotelRooms> getHotelRooms() throws HotelRoomServiceException {
        try {
            List<HotelRooms> hotelRooms = dataAccesObject.loadAllRooms();
            return hotelRooms;
        } catch (DataAccesObjectException daoException) {
            throw new HotelRoomServiceException(daoException.getMessage());
        }
    }


    public void addNewRoom(HotelRooms newHotelRoom) throws HotelRoomServiceException {
        try {
            List<HotelRooms> hotelRooms = dataAccesObject.loadAllRooms();
            hotelRooms.add(newHotelRoom);
            dataAccesObject.saveAllRooms(hotelRooms);
        } catch (DataAccesObjectException daoException) {
            throw new HotelRoomServiceException(daoException.getMessage());
        }
    }

    private int getRoomIndex(List<HotelRooms> hotelRooms, String roomID) {
        for (int i = 0; i < hotelRooms.size(); i++) {
            HotelRooms currentRoom = hotelRooms.get(i);
            if (currentRoom.getRoomID().equals(roomID)) {
                return i;
            }
        }
        return -1;
    }

    public void removeExistingRoom(String roomID) throws HotelRoomServiceException {
        try {
            List<HotelRooms> hotelRooms = dataAccesObject.loadAllRooms();
            int index = getRoomIndex(hotelRooms, roomID);
            if (index != 1 ) {
                hotelRooms.remove(index);
                dataAccesObject.saveAllRooms(hotelRooms);
            }
        } catch (DataAccesObjectException daoException) {
            throw new HotelRoomServiceException(daoException.getMessage());
        }
    }

    }




