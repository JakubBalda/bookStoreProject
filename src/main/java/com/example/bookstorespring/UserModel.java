package com.example.bookstorespring;

import javax.validation.constraints.Email;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.io.Serializable;

public class UserModel implements Serializable {
    @NotBlank(message = "Name is mandatory")
    private String name;
    @NotBlank(message = "Surname is mandatory")
    private String surname;
    @NotBlank(message = "Phone number is mandatory")
    private String phoneNumber;
    @NotBlank(message = "Email is mandatory")
    @Email(message = "Incorrect email")
    private String email;
    @NotBlank(message = "City is mandatory")
    private String city;
    @NotBlank(message = "Street is mandatory")
    private String street;
    @NotBlank(message = "House number is mandatory")
    @Min(1)
    private String houseNumber;
    private String flatNumber;
    @NotBlank(message = "Psotal code is mandatory")
    private String postalCode;
    @NotBlank(message = "Login is mandatory")
    private String login;

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

    public void setFlatNumber(String flatNumber) {
        this.flatNumber = flatNumber;
    }

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
}
