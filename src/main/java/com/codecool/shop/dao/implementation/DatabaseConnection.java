package com.codecool.shop.dao.implementation;

import java.sql.*;
import java.util.ArrayList;
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
            while(resultSet.next()) {
                id = resultSet.getInt(ID_STRING);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return id;
    }

    ArrayList<HashMap<String, Object>> executeSelect(String query, String[] columnNames) {
        ArrayList<HashMap<String, Object>> result = null;
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
            while(resultSet.next()) {
                line = new HashMap<String, Object>();
                for(String column : columnNames) {
                    line.put(column, resultSet.getObject(column));
                }
                result.add(line);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return result;
    }

}

