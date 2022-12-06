package com.example.bookstorespring;

import javax.validation.constraints.*;
import java.io.Serializable;

public class UserModel {
    @NotBlank(message = "Name is mandatory")
    @Size(min = 3, max = 30, message = "Name must be between 3 - 30 signs")
    private String name;
    @NotBlank(message = "Surname is mandatory")
    @Size(min = 3, max = 40, message = "Name must be between 3 - 30 signs")
    private String surname;
    @NotBlank(message = "Phone number is mandatory")
    @Size(min = 12, max = 12, message = "Phone number must be 12 signs length")
    private String phoneNumber;
    @NotBlank(message = "Email is mandatory")
    @Email(message = "Incorrect email")
    private String email;
    @NotBlank(message = "City is mandatory")
    @Size(min = 1, max = 40, message = "City must be between 1 - 40 signs")
    private String city;
    @NotBlank(message = "Street is mandatory")
    @Size(min = 1, max = 40, message = "City must be between 1 - 40 signs")
    private String street;
    @NotBlank(message = "House number is mandatory")
    @Min(1)
    private String houseNumber;

    private String flatNumber;
    @NotBlank(message = "Postal code is mandatory")
    @Size(min = 6, max = 6, message = "Postal code must be 6 signs length")
    private String postalCode;
    @NotBlank(message = "Login is mandatory")
    @Size(min = 4, max = 40, message = "Login must be between 4 - 40 signs")
    private String login;
    @NotBlank
    @Pattern(regexp = "^(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z])(?=.*[!@#&()–[{}]:;',?/*~$^+=<>]).{8,20}$")
    private String password;

    public UserModel(){

    }
    public void setName(String name) {
        this.name = name;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public void setFlatNumber(String flatNumber) { this.flatNumber = flatNumber; }

    public void setPassword(String password) { this.password = password; }

    public void setHouseNumber(String houseNumber) {
        this.houseNumber = houseNumber;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public void setPostalCode(String postalCode) {
        this.postalCode = postalCode;
    }

    public void setStreet(String street) {
        this.street = street;
    }

    public String getName() {
        return name;
    }

    public String getSurname() {
        return surname;
    }

    public String getEmail() {
        return email;
    }

    public String getCity() {
        return city;
    }

    public String getFlatNumber() {
        return flatNumber;
    }

    public String getHouseNumber() {
        return houseNumber;
    }

    public String getLogin() {
        return login;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public String getPostalCode() {
        return postalCode;
    }

    public String getStreet() {
        return street;
    }

    public String getPassword() { return password; }
}
