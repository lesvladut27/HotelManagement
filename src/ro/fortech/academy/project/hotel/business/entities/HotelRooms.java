package ro.fortech.academy.project.hotel.business.entities;

import java.util.Objects;

public class HotelRooms {

    private String roomID;
    private String roomType;
    private String status;
    private float price;

    public HotelRooms(String roomID, String roomType, String status) throws InvalidRoomIdException {
        validateRoomID(roomID);
        this.roomID = roomID;
        this.roomType = roomType;
        this.status = status;

        switch (roomType) {
            case "Single": this.price = 199.99F; break;
            case "Double": this.price = 399.99F; break;
            case "Triple": this.price = 499.99F; break;
        }
    }

    public HotelRooms(String roomID, String roomType, String status, float price) throws InvalidRoomIdException {
        this(roomID, roomType, status);
        this.price = price;
    }

    private void validateRoomID(String roomID) throws InvalidRoomIdException {
        if(!isValidRoomID(roomID)) {
            throw new InvalidRoomIdException(roomID);
        }
    }

    private boolean isValidRoomID(String roomID) {
        return roomID.length() == 6;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        HotelRooms that = (HotelRooms) o;
        return Float.compare(that.price, price) == 0 && Objects.equals(roomID, that.roomID) && Objects.equals(roomType, that.roomType) && Objects.equals(status, that.status);
    }

    @Override
    public int hashCode() {
        return Objects.hash(roomID, roomType, status, price);
    }

    @Override
    public String toString() {
        return "HotelRooms{" +
                "roomID='" + roomID + '\'' +
                ", roomType='" + roomType + '\'' +
                ", status='" + status + '\'' +
                ", price=" + price +
                '}';
    }

    public String getRoomID() {
        return roomID;
    }
    public void setRoomID(String roomID) throws InvalidRoomIdException {
        validateRoomID(roomID);
        this.roomID = roomID;
    }

    public String getRoomType() {
        return roomType;
    }
    public void setRoomType(String roomType) {
        this.roomType = roomType;
    }

    public String getStatus() {
        return status;
    }
    public void setStatus(String status) {
        this.status = status;
    }

    public float getPrice() {
        return price;
    }
    public void setPrice(float price) {
        this.price = price;
    }
}

