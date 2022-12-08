package com.example.bookstorespring.models;

public class LoginModel {

    private String login;
    private String password;

    private String role;

    private String userID;

    public void setLogin(String login) {
        this.login = login;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void setRole(String role) { this.role = role; }

    public void setUserID(String userID) { this.userID = userID; }

    public String getLogin() {
        return login;
    }

    public String getPassword() {
        return password;
    }

    public String getRole() { return role; }

    public String getUserID() { return userID; }
}
