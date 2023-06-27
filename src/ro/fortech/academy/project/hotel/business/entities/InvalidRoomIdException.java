package ro.fortech.academy.project.hotel.business.entities;

public class InvalidRoomIdException extends Exception {

    private final String roomID;

    public InvalidRoomIdException(String roomID) {
        this.roomID = roomID;
    }

    public String getRoomID() {
        return roomID;
    }
}
