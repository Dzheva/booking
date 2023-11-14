package models;

import java.io.Serializable;

public record Passenger(String fistName, String lastName) implements Serializable {
    @Override
    public String toString() {
        return "Passenger{" +
                "fistName='" + fistName + '\'' +
                ", lastName='" + lastName + '\'' +
                '}';
    }
}
