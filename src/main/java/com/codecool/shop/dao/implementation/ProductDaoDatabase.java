package com.codecool.shop.dao.implementation;

import com.codecool.shop.dao.ProductDao;
import com.codecool.shop.model.Product;
import com.codecool.shop.model.ProductCategory;
import com.codecool.shop.model.Supplier;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class ProductDaoDatabase extends DatabaseConnection implements ProductDao{

    private final String TABLE_NAME = "products";
    private final String SELECT_QUERY = "SELECT * FROM";
    private final String[] TABLE_COLUMNS = {"name",
                                            "id",
                                            "defaultprice",
                                            "currency",
                                            "description",
                                            "size",
                                            "color",
                                            "category_id",
                                            "supplier_id",
                                            "image"};

    @Override
    public void add(Product product) {
        insertInto(product);
    }

    private void insertInto(Product product) {
        String[] emptyList = {};
        String query = "INSERT INTO " + TABLE_NAME + " (name, defaultprice, currency, description, size, color, category_id, supplier_id)"
                        + " VALUES (" + product.getName() + ", " +
                                        product.getDefaultPrice() + ", " +
                                        product.getDefaultCurrency() + ", " +
                                        product.getDescription() + ", " +
                                        product.getSize() + ", " +
                                        product.getColor() + ", " +
                                        product.getProductCategory().getId() + ", " +
                                        product.getSupplier().getId() + ");";
        executeQuery(query, emptyList);
    }

    @Override
    public Product find(int id) {
        String criteria = " WHERE id = " + id;
        return select(criteria).get(0);
    }

    private ArrayList<Product> select(String criteria) {
        ArrayList<HashMap<String, Object>> result;
        ArrayList<Product> products = null;
        String query = SELECT_QUERY + " " + TABLE_NAME + criteria + ";";
        result = executeSelect(query, TABLE_COLUMNS);
        for(HashMap<String, Object> productInfo : result) {
            products.add(createProductObject(productInfo));
        }
        return products;
    }

    private Product createProductObject(HashMap<String, Object> productData) {
        return new Product((String)productData.get("name"),
                                            (long) productData.get("defaultprice"),
                                            (String) productData.get("currency"),
                                            (String) productData.get("description"),
                                            (ProductCategory) productData.get("category_id"),
                                            (Supplier) productData.get("supplier_id"),
                                            (String) productData.get("size"),
                                            (String) productData.get("color"));
    }

    @Override
    public void remove(int id) {
        delete(id);
    }

    private void delete(int id) {
        String query = "DELETE FROM " + TABLE_NAME + " WHERE id = " + id + ";";
        String[] nullList = null;
        executeQuery(query, nullList);
    }

    @Override
    public List<Product> getAll() {
        String criteria = "";
        return select(criteria);
    }

    @Override
    public List<Product> getBy(Supplier supplier) {
        int supplierId = supplier.getId();
        String criteria = " WHERE supplier_id = " + supplierId;
        return select(criteria);
    }

    @Override
    public List<Product> getBy(ProductCategory productCategory) {
        int category_id = productCategory.getId();
        String criteria = " WHERE category_id = " + category_id;
        return select(criteria);
    }
}
