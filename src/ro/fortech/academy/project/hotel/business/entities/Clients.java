package ro.fortech.academy.project.hotel.business.entities;

import java.util.Objects;

public class Clients extends Person {

    private float budget;

    public Clients(String ssn, String firstName, String lastName, float budget) throws InvalidSsnException {
        super(ssn, firstName, lastName);
        validateSsn(ssn);
        this.budget = budget;
    }

    private void validateSsn(String ssn) throws InvalidSsnException {
        if (!isValidSsn(ssn)) {
            throw new InvalidSsnException(ssn);
        }
    }

    private boolean isValidSsn(String ssn) {
        return ssn.length() == 6;
    }

    @Override
    public String toString() {
        return "Clients{" +
                ", budget=" + budget +
                "ssn='" + getSsn() + '\'' +
                ", firstName='" + getFirstName() + '\'' +
                ", lastName='" + getLastName() + '\'' +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Clients clients = (Clients) o;
        return Float.compare(clients.budget, budget) == 0 && Objects.equals(getSsn(), clients.getSsn()) && Objects.equals(getFirstName(), clients.getFirstName()) && Objects.equals(getLastName(), clients.getLastName());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getSsn(), getFirstName(), getLastName(), getBudget());
    }

    @Override
    public void setSsn(String ssn) throws InvalidSsnException {
        validateSsn(ssn);
        super.setSsn(ssn);
    }

    public float getBudget() {
        return budget;
    }

    public void setBudget(float budget) {
        this.budget = budget;
    }
}