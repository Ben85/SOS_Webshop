package com.codecool.shop.dao.implementation;

import com.codecool.shop.dao.SupplierDao;
import com.codecool.shop.model.Supplier;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class SupplierDaoDatabase extends DatabaseConnection implements SupplierDao {

    private final String TABLE_NAME = "suppliers";
    private final String[] COLUMN_NAMES = {"id", "name", "description"};

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
        HashMap<String, Object> supplierData = select(id);
        String name = (String) supplierData.get("name");
        String description = (String) supplierData.get("description");
        return new Supplier(name, description);
    }

    private HashMap<String, Object> select(int id) {
        final int singleResultIndex = 0;
        String query = "SELECT * FROM " + TABLE_NAME + " WHERE id = " + id + ";";
        return executeSelect(query, COLUMN_NAMES).get(singleResultIndex);
    }

    @Override
    public void remove(int id) {
        String[] parameters = {Integer.toString(id)};
        delete(parameters);
    }

    private void delete(String[] parameters) {
        String query = "DELETE FROM " + TABLE_NAME + " WHERE id = ?;";
        executeQuery(query, parameters);
    }

    @Override
    public List<Supplier> getAll() {
        ArrayList<Supplier> suppliers = new ArrayList<>();
        ArrayList<HashMap<String, Object>> records = selectAll();
        for (HashMap<String, Object> record : records) {
            String name = (String) record.get("name");
            String description = (String) record.get("description");
            suppliers.add(new Supplier(name, description));
        }
        return suppliers;
    }

    private ArrayList<HashMap<String, Object>> selectAll() {
        String query = "SELECT * FROM " + TABLE_NAME + ";";
        return executeSelect(query, COLUMN_NAMES);
    }

}
