package com.codecool.shop.dao.implementation;

import com.codecool.shop.dao.ProductCategoryDao;
import com.codecool.shop.model.ProductCategory;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class ProductCategoryDaoDatabase extends DatabaseConnection implements ProductCategoryDao{

    private static ProductCategoryDaoDatabase instance = null;
    private final String TABLE_NAME = "product_categories";
    private final String[] COLUMN_NAMES = {"id", "name", "department", "description"};

    private ProductCategoryDaoDatabase() {
    }

    public static ProductCategoryDaoDatabase getInstance() {
        if (instance == null) {
            instance = new ProductCategoryDaoDatabase();
        }
        return instance;
    }

    @Override
    public void add(ProductCategory category) {
        insertInto(category);

    }

    private void insertInto(ProductCategory category) {
        String queryString = "INSERT INTO " + TABLE_NAME + " (name, department, description) VALUES (?, ?, ?) RETURNING id;";
        int newId = executeQuery(queryString, category);
        category.setId(newId);
    }

    @Override
    public ProductCategory find(int id) {
        HashMap<String, Object> categoryData = select(id);
        String name = (String) categoryData.get("name");
        String department = (String) categoryData.get("department");
        String description = (String) categoryData.get("description");
        return new ProductCategory(id, name, department, description);
    }

    @Override
    public void remove(int id) {
        delete(id);
    }

    private void delete(int id) {
        String queryString = "DELETE FROM " + TABLE_NAME + " WHERE id = ?;";
        executeDelete(queryString, id);
    }

    @Override
    public List<ProductCategory> getAll() {
        ArrayList<ProductCategory> categories = new ArrayList<>();
        ArrayList<HashMap<String, Object>> records = selectAll();
        for (HashMap<String, Object> record : records) {
            int id = (int) record.get("id");
            String name = (String) record.get("name");
            String department = (String) record.get("department");
            String description = (String) record.get("description");
            categories.add(new ProductCategory(id, name, department, description));
        }
        return categories;
    }

    private HashMap<String, Object> select(int id){
        final int singleResultIndex = 0;
        String queryString = "SELECT * FROM " + TABLE_NAME + " WHERE id = " + id;
        return executeSelect(queryString, COLUMN_NAMES).get(singleResultIndex);
    }

    private ArrayList<HashMap<String, Object>> selectAll() {
        String queryString = "SELECT * FROM " + TABLE_NAME + ";";
        return executeSelect(queryString, COLUMN_NAMES);
    }
}
