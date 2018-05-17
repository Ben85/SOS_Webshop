package com.codecool.shop.dao.implementation;

import com.codecool.shop.model.*;

import java.sql.*;
import java.util.ArrayList;
import java.util.Currency;
import java.util.HashMap;

public abstract class DatabaseConnection {

    private static final String JDBC_DRIVER = "org.postgresql.Driver";
    private static final String JDBC_URL = "jdbc:postgresql://localhost:5432/shopcool";
    private static final String USERNAME = System.getenv("DB_USERNAME");
    private static final String PASSWORD = System.getenv("DB_PASSWORD");
    private static final String ID_STRING = "id";

    private static Connection getConnection() throws SQLException {
        return DriverManager.getConnection(JDBC_URL, USERNAME, PASSWORD);
    }

    int executeQuery(String query, String[] parameters) {
        int id = -1; //returns -1 when no ID return is needed
        try {
            Class.forName(JDBC_DRIVER);
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        try (Connection connection = getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(query)
        ) {
            if (parameters != null) {
                for (int i = 0; i < parameters.length; i++) {
                    int parameterIndex = i + 1;
                    preparedStatement.setString(parameterIndex, parameters[i]);
                }
            }
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                id = resultSet.getInt(ID_STRING);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return id;
    }

    int executeQuery(String query, Product product) {
        int id = -1; //returns -1 when no ID return is needed
        try {
            Class.forName(JDBC_DRIVER);
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        try (Connection connection = getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(query)) {

            String name = product.getName();
            long defaultPrice = product.getDefaultPrice();
            Currency defaultCurrency = product.getDefaultCurrency();
            String description = product.getDescription();
            String size = product.getSize();
            String color = product.getColor();
            ProductCategory category = product.getProductCategory();
            Supplier supplier = product.getSupplier();

            preparedStatement.setString(1, name);
            preparedStatement.setLong(2, defaultPrice);
            preparedStatement.setString(3, defaultCurrency.toString());
            preparedStatement.setString(4, description);
            preparedStatement.setString(5, size);
            preparedStatement.setString(6, color);
            preparedStatement.setInt(7, category.getId());
            preparedStatement.setInt(8, supplier.getId());

            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                id = resultSet.getInt(ID_STRING);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return id;
    }

    int executeQuery(String query, ProductCategory category) {
        int id = -1; //returns -1 if not successful
        try {
            Class.forName(JDBC_DRIVER);
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        try (Connection connection = getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(query)
        ) {
            preparedStatement.setString(1, category.getName());
            preparedStatement.setString(2, category.getDepartment());
            preparedStatement.setString(3, category.getDescription());
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                id = resultSet.getInt(ID_STRING);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return id;
    }

    int executeQuery(String query, Supplier supplier) {
        int id = -1; //returns -1 if not successful
        try {
            Class.forName(JDBC_DRIVER);
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        try (Connection connection = getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(query)
        ) {
            preparedStatement.setString(1, supplier.getName());
            preparedStatement.setString(2, supplier.getDescription());
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                id = resultSet.getInt(ID_STRING);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return id;
    }

    int executeQuery(String query, Customer customer) {
        int id = -1; //returns -1 if not successful
        try {
            Class.forName(JDBC_DRIVER);
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        try (Connection connection = getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(query)
        ) {
            preparedStatement.setString(1, customer.getFirstName());
            preparedStatement.setString(2, customer.getLastName());
            preparedStatement.setString(3, customer.getHashedPassword());
            preparedStatement.setString(4, customer.getEmail());
            preparedStatement.setInt(5, customer.getPhoneNum());
            preparedStatement.setString(6, customer.getZipCode());
            preparedStatement.setString(7, customer.getCity());
            preparedStatement.setString(8, customer.getAddress());
            preparedStatement.setString(9, customer.getBillingZipCode());
            preparedStatement.setString(10, customer.getBillingCity());
            preparedStatement.setString(11, customer.getBillingAddress());
            preparedStatement.setString(12, customer.getUsername());
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                id = resultSet.getInt(ID_STRING);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return id;
    }

    ArrayList<HashMap<String, Object>> executeSelect(String query, String[] columnNames) {
        ArrayList<HashMap<String, Object>> result = new ArrayList<>();
        HashMap<String, Object> line;
        try {
            Class.forName(JDBC_DRIVER);
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        try (Connection connection = getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(query)
        ) {
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                line = new HashMap<>();
                for (String column : columnNames) {
                    line.put(column, resultSet.getObject(column));
                }
                result.add(line);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return result;
    }

    void executeDelete(String query, Object objectToDelete) {
        int id = getId(objectToDelete);
        try {
            Class.forName(JDBC_DRIVER);
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        try (Connection connection = getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(query)
        ) {
            preparedStatement.setInt(1, id);
            preparedStatement.execute();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    private int getId(Object objectToDelete) {
        int id = 0;
        if (objectToDelete instanceof Product) {
            Product product = (Product) objectToDelete;
            id = product.getId();
        } else if (objectToDelete instanceof ProductCategory) {
            ProductCategory productCategory = (ProductCategory) objectToDelete;
            id = productCategory.getId();
        } else if (objectToDelete instanceof Supplier) {
            Supplier supplier = (Supplier) objectToDelete;
            id = supplier.getId();
        } else if (objectToDelete instanceof ShoppingCart) {
            ShoppingCart shoppingCart = (ShoppingCart) objectToDelete;
            id = shoppingCart.getId();
        } else if (objectToDelete instanceof Customer) {
            Customer customer = (Customer) objectToDelete;
            id = customer.getId();
        }
        return id;
    }

}

