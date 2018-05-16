package com.codecool.shop.dao.implementation;

import com.codecool.shop.dao.CustomerDao;
import com.codecool.shop.model.Customer;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class CustomerDaoDatabase extends DatabaseConnection implements CustomerDao {

    private static CustomerDaoDatabase instance = null;
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

    private CustomerDaoDatabase() {
    }

    public static CustomerDaoDatabase getInstance() {
        if (instance == null) {
            instance = new CustomerDaoDatabase();
        }
        return instance;
    }

    @Override
    public void add(Customer customer) {
        String[] parameters = {
                customer.getFirstName(),
                customer.getLastName(),
                customer.getHashedPassword(),
                customer.getEmail(),
                String.valueOf(customer.getPhoneNum()),
                customer.getZipCode(),
                customer.getCity(),
                customer.getAddress(),
                customer.getBillingZipCode(),
                customer.getBillingCity(),
                customer.getBillingAddress(),
                customer.getUsername()
        };
        insertInto(customer);
    }

    private void insertInto(Customer customer) {
        StringBuilder sb = new StringBuilder();
        int indexOfItemBeforeLast = COLUMN_NAMES.length - 2;
        int indexOfLastItem = COLUMN_NAMES.length - 1;
        for (int i = 1; i <= indexOfItemBeforeLast; i++) {
            sb.append(COLUMN_NAMES[i]);
            sb.append(", ");
        }
        sb.append(COLUMN_NAMES[indexOfLastItem]);
        String query = "INSERT INTO " + TABLE_NAME + " (" + sb.toString() + ") VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?) RETURNING id;";
        int newId = executeQuery(query, customer);
        customer.setId(newId);
    }

    @Override
    public Customer find(int id) {
        HashMap<String, Object> customerData = select(id);
        return new Customer(
                (String) customerData.get("first_name"),
                (String) customerData.get("last_name"),
                (String) customerData.get("hashed_password"),
                (String) customerData.get("email"),
                (Integer) customerData.get("phone_number"),
                (String) customerData.get("zip_code"),
                (String) customerData.get("city"),
                (String) customerData.get("address"),
                (String) customerData.get("billing_zip_code"),
                (String) customerData.get("billing_city"),
                (String) customerData.get("billing_address"),
                (String) customerData.get("username")
        );
    }

    private HashMap<String, Object> select(int id) {
        final int singleResultIndex = 0;
        String query = "SELECT * FROM " + TABLE_NAME + " WHERE id = " + id + ";";
        return executeSelect(query, COLUMN_NAMES).get(singleResultIndex);
    }

    @Override
    public void remove(int id) {
        Customer customer = Customer.getCustomerById(id);
        delete(customer);
    }

    private void delete(Customer customer) {
        String query = "DELETE FROM " + TABLE_NAME + " WHERE id = ?;";
        executeDelete(query, customer);
    }

    @Override
    public List<Customer> getAll() {
        ArrayList<Customer> customers = new ArrayList<>();
        ArrayList<HashMap<String, Object>> records = selectAll();
        for (HashMap<String, Object> record : records) {
            customers.add(new Customer(
                            (String) record.get("first_name"),
                            (String) record.get("last_name"),
                            (String) record.get("hashed_password"),
                            (String) record.get("email"),
                            (Integer) record.get("phone_number"),
                            (String) record.get("zip_code"),
                            (String) record.get("city"),
                            (String) record.get("address"),
                            (String) record.get("billing_zip_code"),
                            (String) record.get("billing_city"),
                            (String) record.get("billing_address"),
                            (String) record.get("username")
                    )
            );
        }
        return customers;
    }

    private ArrayList<HashMap<String, Object>> selectAll() {
        String query = "SELECT * FROM " + TABLE_NAME + ";";
        return executeSelect(query, COLUMN_NAMES);
    }

}
