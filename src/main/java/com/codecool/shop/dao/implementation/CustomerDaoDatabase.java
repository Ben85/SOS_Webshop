package com.codecool.shop.dao.implementation;

import com.codecool.shop.dao.CustomerDao;
import com.codecool.shop.model.Customer;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;

public class CustomerDaoDatabase extends DatabaseConnection implements CustomerDao {

    private final String TABLE_NAME = "customers";
    private final String[] COLUMN_NAMES = {
            "id",
            "first_name",
            "last_name",
            "hashed_password",
            "email",
            "phone_number",
            "zip_code",
            "city",
            "address",
            "billing_zip_code",
            "billing_city",
            "billing_address",
            "username"
    };

    @Override
    public void add(Customer customer) {
        String firstName = customer.getFirstName();
        String lastName = customer.getLastName();
        String hashedPassword = customer.getHashedPassword();
        String email = customer.getEmail();
        String phoneNumber = String.valueOf(customer.getPhoneNum());
        String zipCode = customer.getZip();
        String city = customer.getCity();
        String address = customer.getAddress();
        String billingZipCode = customer.getbZip();
        String billingCity = customer.getbCity();
        String billingAddress = customer.getbAddress();
        String username = customer.getUsername();
        String[] parameters = {firstName, lastName, hashedPassword, email, phoneNumber, zipCode, city, address, billingZipCode, billingCity, billingAddress, username};
        insertInto(parameters);
    }

    private void insertInto(String[] parameters) {
        StringBuilder sb = new StringBuilder();
        int itemBeforeLastIndex = COLUMN_NAMES.length - 2;
        int lastItemIndex = COLUMN_NAMES.length - 1;
        for (int i = 1; i <= itemBeforeLastIndex; i++) {
            sb.append(COLUMN_NAMES[i]);
            sb.append(", ");
        }
        sb.append(COLUMN_NAMES[lastItemIndex]);
        String query = "INSERT INTO " + TABLE_NAME + " (" + sb.toString() + ") VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?);";
        executeQuery(query, parameters);
    }

//    @Override
//    public Supplier find(int id) {
//        HashMap<String, Object> supplierData = select(id);
//        String name = (String) supplierData.get("name");
//        String description = (String) supplierData.get("description");
//        return new Supplier(name, description);
//    }
//
//    private HashMap<String, Object> select(int id) {
//        final int singleResultIndex = 0;
//        String query = "SELECT * FROM " + TABLE_NAME + " WHERE id = " + id + ";";
//        return executeSelect(query, COLUMN_NAMES).get(singleResultIndex);
//    }
//
//    @Override
//    public void remove(int id) {
//        String[] parameters = {Integer.toString(id)};
//        delete(parameters);
//    }
//
//    private void delete(String[] parameters) {
//        String query = "DELETE FROM " + TABLE_NAME + " WHERE id = ?;";
//        executeQuery(query, parameters);
//    }
//
//    @Override
//    public List<Supplier> getAll() {
//        ArrayList<Supplier> suppliers = new ArrayList<>();
//        ArrayList<HashMap<String, Object>> records = selectAll();
//        for (HashMap<String, Object> record : records) {
//            String name = (String) record.get("name");
//            String description = (String) record.get("description");
//            suppliers.add(new Supplier(name, description));
//        }
//        return suppliers;
//    }
//
//    private ArrayList<HashMap<String, Object>> selectAll() {
//        String query = "SELECT * FROM " + TABLE_NAME + ";";
//        return executeSelect(query, COLUMN_NAMES);
//    }

}
