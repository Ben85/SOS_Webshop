package com.codecool.shop.model;

import java.util.HashMap;

public class Customer {

    private static int count = 0;
    private int id;
    private String firstName;
    private String lastName;
    private String hashedPassword;
    private String email;
    private int phoneNum;
    private String zipCode;
    private String city;
    private String address;
    private String billingZipCode;
    private String billingCity;
    private String billingAddress;
    private String username;
    private String isSameAddress;
    private HashMap<String, String> userData = new HashMap<>();

    public Customer() {
        this.id = ++count;
    }

    public Customer(HashMap<String, String> userInput) {
        this.id = ++count;
        userData = (HashMap<String, String>) userInput.clone();
    }

    public Customer(
            String firstName,
            String lastName,
            String hashedPassword,
            String email,
            int phoneNum,
            String zipCode,
            String city,
            String address,
            String billingZipCode,
            String billingCity,
            String billingAddress,
            String username,
            String isSameAddress
    ) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.hashedPassword = hashedPassword;
        this.email = email;
        this.phoneNum = phoneNum;
        this.zipCode = zipCode;
        this.city = city;
        this.address = address;
        this.billingZipCode = billingZipCode;
        this.billingCity = billingCity;
        this.billingAddress = billingAddress;
        this.username = username;
        this.isSameAddress = isSameAddress;
    }

    public Customer(
            String firstName,
            String lastName,
            String hashedPassword,
            String email,
            int phoneNum,
            String zipCode,
            String city,
            String address,
            String billingZipCode,
            String billingCity,
            String billingAddress,
            String username
    ) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.hashedPassword = hashedPassword;
        this.email = email;
        this.phoneNum = phoneNum;
        this.zipCode = zipCode;
        this.city = city;
        this.address = address;
        this.billingZipCode = billingZipCode;
        this.billingCity = billingCity;
        this.billingAddress = billingAddress;
        this.username = username;
    }

    public Customer(
            String firstName,
            String lastName,
            String hashedPassword,
            String email,
            int phoneNum,
            String zipCode,
            String city,
            String address,
            String username
    ) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.hashedPassword = hashedPassword;
        this.email = email;
        this.phoneNum = phoneNum;
        this.zipCode = zipCode;
        this.city = city;
        this.address = address;
        this.username = username;
    }

    public boolean getIsSameAddress() {
        return Boolean.valueOf(isSameAddress);
    }

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public String getHashedPassword() {
        return hashedPassword;
    }

    public String getEmail() {
        return email;
    }

    public int getPhoneNum() {
        return phoneNum;
    }

    public String getBillingZipCode() {
        return billingZipCode;
    }

    public String getZipCode() {
        return zipCode;
    }

    public String getCity() {
        return city;
    }

    public String getBillingCity() {
        return billingCity;
    }

    public String getAddress() {
        return address;
    }

    public String getBillingAddress() {
        return billingAddress;
    }

    public String getUsername() {
        return username;
    }

    public HashMap<String, String> getUserData() {
        return userData;
    }

    public void setUserData(HashMap<String, String> userData) {
        this.userData = userData;
    }


    public int getId() {
        return id;
    }

    public boolean isSameAddress() {
        return this.address.equals(this.billingAddress) && this.city.equals(this.billingCity) && this.zipCode.equals(this.billingZipCode);
    }
}
