package models;

import java.io.Serializable;

public record User(int id, String login, String password,
                   String firstName, String lastName) implements Serializable {
    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", login='" + login + '\'' +
                ", password='" + password + '\'' +
                ", firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                '}';
    }
}
