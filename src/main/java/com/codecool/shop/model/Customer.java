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
    private String zip;
    private String city;
    private String address;
    private String bZip;
    private String bCity;
    private String bAddress;
    private String username;
    private String isSameAddress;
    HashMap<String, String> userData = new HashMap<>();

    public Customer(){ this.id = ++count; }

    public Customer(HashMap<String, String> userInput){
        this.id = ++count;
        userData = (HashMap<String, String>) userInput.clone();
    }

    public Customer(String firstName, String lastName, String hashedPassword, String email, int phoneNum, String bZip, String zip, String city, String bCity, String address, String bAddress, String username, String isSameAddress) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.hashedPassword = hashedPassword;
        this.email = email;
        this.phoneNum = phoneNum;
        this.zip = zip;
        this.city = city;
        this.address = address;
        this.bZip = bZip;
        this.bCity = bCity;
        this.bAddress = bAddress;
        this.username = username;
        this.isSameAddress = isSameAddress;
    }

    public Customer(String firstName, String lastName, String hashedPassword, String email, int phoneNum, String zip, String city, String address, String username) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.hashedPassword = hashedPassword;
        this.email = email;
        this.phoneNum = phoneNum;
        this.zip = zip;
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

    public void setEmail(String email) {
        this.email = email;
    }

    public int getPhoneNum() {
        return phoneNum;
    }

    public void setPhoneNum(int phoneNum) {
        this.phoneNum = phoneNum;
    }

    public String getbZip() {
        return bZip;
    }

    public void setbZip(String bZip) {
        this.bZip = bZip;
    }

    public String getZip() {
        return zip;
    }

    public void setZip(String zip) {
        this.zip = zip;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getbCity() {
        return bCity;
    }

    public void setbCity(String bCity) {
        this.bCity = bCity;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getbAddress() {
        return bAddress;
    }

    public void setbAddress(String bAddress) {
        this.bAddress = bAddress;
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

    public boolean isSameAddress(){
        return this.address.equals(this.bAddress) && this.city.equals(this.bCity) && this.zip.equals(this.bZip);
    }
}
