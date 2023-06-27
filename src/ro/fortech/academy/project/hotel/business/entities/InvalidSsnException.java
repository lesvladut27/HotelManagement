package ro.fortech.academy.project.hotel.business.entities;

public class InvalidSsnException extends Exception{
    private final String ssn;

    public InvalidSsnException(String ssn) {
        this.ssn = ssn;
    }

    public String getSsn() {
        return getSsn();
    }
}
