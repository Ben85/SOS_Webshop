package com.codecool.shop.model;

import java.util.ArrayList;
import java.util.List;

public class Supplier extends BaseModel {
    private List<Product> products;
    private static List<Supplier> suppliers = new ArrayList<>();
    private int id;

    public Supplier(String name, String description) {
        super(name, description);
        this.products = new ArrayList<>();
        suppliers.add(this);
    }

    public void setProducts(ArrayList<Product> products) {
        this.products = products;
    }

    public List<Product> getProducts() {
        return this.products;
    }

    public void addProduct(Product product) {
        this.products.add(product);
    }

    public String toString() {
        return String.format("id: %1$d, " +
                        "name: %2$s, " +
                        "description: %3$s",
                this.id,
                this.name,
                this.description
        );
    }

    public static Supplier getSupplierById(int id) {
        for (Supplier supplier : suppliers) {
            if (supplier.id == id) return supplier;
        }
        return null;
    }

    @Override
    public int getId() {
        return id;
    }

    @Override
    public void setId(int id) {
        this.id = id;
    }
}