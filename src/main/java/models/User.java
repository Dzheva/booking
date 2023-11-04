package models;

import java.io.Serializable;
import java.util.List;

public class User implements Serializable {
    private final String login;
    private final String password;
    private final String firstName;
    private final String lastName;
    private List<Reservation> reservations;

    public User(String login, String password,
                String firstName, String lastName, List<Reservation> reservations) {
        this.login = login;
        this.password = password;
        this.firstName = firstName;
        this.lastName = lastName;
        this.reservations = reservations;
    }

    public String getLogin() {
        return login;
    }

    public String getPassword() {
        return password;
    }

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public List<Reservation> getReservations() {
        return reservations;
    }

    public void setReservations(List<Reservation> reservations) {
        this.reservations = reservations;
    }
}
