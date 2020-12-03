package com.example.ffbfv14;

public class User {
    public String firstName;
    public String surname;
    public String email;
    public String type;

    public User() { }

    public User(String firstName, String surname, String email, String type) {
        this.firstName = firstName;
        this.surname = surname;
        this.email = email;
        this.type = type;
    }



    public String getFirstName() { return firstName; }

    public void setFirstName(String firstName) { this.firstName = firstName; }

    public String getSurname() { return surname; }

    public void setSurname(String surname) { this.surname = surname; }

    public String getEmail() { return email; }

    public void setEmail(String email) { this.email = email; }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }
}
