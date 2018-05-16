package com.codecool.shop.dao.implementation;

import com.codecool.shop.dao.SupplierDao;
import com.codecool.shop.model.Supplier;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class SupplierDaoDatabase extends DatabaseConnection implements SupplierDao {

    private static SupplierDaoDatabase instance = null;
    private final String TABLE_NAME = "suppliers";
    private final String[] COLUMN_NAMES = {
            "id",
            "name",
            "description"
    };

    private SupplierDaoDatabase() {
    }

    public static SupplierDaoDatabase getInstance() {
        if (instance == null) {
            instance = new SupplierDaoDatabase();
        }
        return instance;
    }

    @Override
    public void add(Supplier supplier) {
        insertInto(supplier);
    }

    private void insertInto(Supplier supplier) {
        String query = "INSERT INTO " + TABLE_NAME + " (name, description) VALUES (?, ?) RETURNING id;";
        int newId = executeQuery(query, supplier);
        supplier.setId(newId);
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
        Supplier supplier = Supplier.getSupplierById(id);
        delete(supplier);
    }

    private void delete(Supplier supplier) {
        String query = "DELETE FROM " + TABLE_NAME + " WHERE id = ?;";
        executeDelete(query, supplier);
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
