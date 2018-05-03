package com.codecool.shop.dao.implementation;

import java.sql.* ;
import java.util.HashMap;

public abstract class DatabaseConnection {
    private static final String JDBC_DRIVER = "org.postgresql.Driver";
    private static final String JDBC_URL = "jdbc:postgresql://localhost:5432/shopcool";

    private static final String USERNAME = System.getenv("DB_USERNAME");
    private static final String PASSWORD = System.getenv("DB_PASSWORD");

    private static Connection getConnection() throws SQLException {
        return DriverManager.getConnection(JDBC_URL, USERNAME, PASSWORD);
    }

    HashMap<String, Object> executeQuery(String query, String[] parameters) {
        HashMap<String, Object> result = null;
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
            result = new HashMap<String, Object>();
            while(resultSet.next()) {
                for(String parameter : parameters) {
                    result.put(parameter, resultSet.getObject(parameter));
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return result;
    }
}

