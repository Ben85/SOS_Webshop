package com.codecool.shop.dao.implementation;

import com.codecool.shop.dao.SupplierDao;
import com.codecool.shop.model.Supplier;

import java.util.HashMap;
import java.util.List;

public class SupplierDaoDatabase extends DatabaseConnection implements SupplierDao {

    private final String TABLE_NAME = "suppliers";

    @Override
    public void add(Supplier supplier) {
        String name = supplier.getName();
        String description = supplier.getDescription();
        String[] parameters = {name, description};
        insertInto(parameters);
    }

    private void insertInto(String[] parameters) {
        String query = "INSERT INTO " + TABLE_NAME + " (name, description) VALUES (?, ?);";
        executeQuery(query, parameters);
    }

    @Override
    public Supplier find(int id) {
        return null;
    }

    private HashMap<String, Object> select(int id) {
        return null;
    }

    @Override
    public void remove(int id) {

    }

    @Override
    public List<Supplier> getAll() {
        return null;
    }

}
