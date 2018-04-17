package com.codecool.shop.model;

import java.util.HashMap;

public class UserClass {

    private String name;
    private int id;
    private static int count = 0;
    private String email;
    private String phoneNum;
    private String bZip;
    private String zip;
    private String city;
    private String bCity;
    private String address;
    private String bAddress;
    HashMap<String, String> userData = new HashMap<>();

    UserClass(){ this.id = ++count; }

    UserClass(HashMap<String, String> userInput){
        this.id = ++count;
        userData = (HashMap<String, String>) userInput.clone();
    }

    public UserClass(String name, String email, String phoneNum, String bZip, String zip, String city, String bCity, String address, String bAddress) {
        this.name = name;
        this.email = email;
        this.phoneNum = phoneNum;
        this.bZip = bZip;
        this.zip = zip;
        this.city = city;
        this.bCity = bCity;
        this.address = address;
        this.bAddress = bAddress;
    }

    public UserClass(String name, String email, String phoneNum, String zip, String city, String address) {
        this.name = name;
        this.email = email;
        this.phoneNum = phoneNum;
        this.zip = zip;
        this.city = city;
        this.address = address;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPhoneNum() {
        return phoneNum;
    }

    public void setPhoneNum(String phoneNum) {
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

    public HashMap<String, String> getUserData() {
        return userData;
    }

    public void setUserData(HashMap<String, String> userData) {
        this.userData = userData;
    }


    public int getId() {

        return id;
    }
}
