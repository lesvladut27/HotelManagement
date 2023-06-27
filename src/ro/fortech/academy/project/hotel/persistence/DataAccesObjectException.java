package ro.fortech.academy.project.hotel.persistence;

import java.time.LocalDateTime;

public class DataAccesObjectException extends Exception {

    private final LocalDateTime moment;

    public DataAccesObjectException(String message, LocalDateTime moment) {
        super(message);
        this.moment = moment;
    }
}
