package com.codecool.shop.dao.implementation;

import com.codecool.shop.model.Product;
import com.codecool.shop.model.ProductCategory;
import com.codecool.shop.model.Supplier;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Random;

import static org.junit.jupiter.api.Assertions.*;

class ProductDaoDatabaseTest {
    Product product;
    private Random random = new Random();

    @BeforeEach
    public void init() {
        ProductCategory cat = new ProductCategory(random.nextInt(666666), "category name 1", "department 1", "no description");
        Supplier sup = new Supplier(" supplier name 1", "description");

        Product product = new Product("prod name 1", random.nextInt(666666), 100L, "HUF", "No", cat, sup, "XL", "black");
        this.product = product;

    }

    @Test
    public void addToDatabase() {
        ProductDaoDatabase productDaoDatabase = ProductDaoDatabase.getInstance();
        int sizeBefore = productDaoDatabase.getAll().size();
        productDaoDatabase.add(product);

        assertEquals(sizeBefore + 1, productDaoDatabase.getAll().size());
    }
}