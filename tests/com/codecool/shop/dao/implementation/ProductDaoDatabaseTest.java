package com.codecool.shop.dao.implementation;

import com.codecool.shop.model.Product;
import com.codecool.shop.model.ProductCategory;
import com.codecool.shop.model.Supplier;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class ProductDaoDatabaseTest {
    Product product;

    @BeforeEach
    public void init() {
        ProductCategory cat = new ProductCategory("category name 1", "department 1", "no description");
        Supplier sup = new Supplier(" supplier name 1", "description");

        Product product = new Product("prod name 1", 100L, "HUF", "No", cat, sup, "XL", "black");
        this.product = product;

    }

    @Test
    public void addToDatabase() {
        ProductDaoDatabase productDaoDatabase = ProductDaoDatabase.getInstance();
        productDaoDatabase.add(product);

        assertEquals(2, productDaoDatabase.getAll().size());
    }
}