package com.codecool.shop.dao.implementation;

import com.codecool.shop.model.ProductCategory;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Random;

import static org.junit.jupiter.api.Assertions.assertEquals;

class ProductCategoryDaoDatabaseTest {

    private ProductCategoryDaoDatabase categoryDaoDb;
    private Random random = new Random();

    @BeforeEach
    void setUp() {
        categoryDaoDb = ProductCategoryDaoDatabase.getInstance();
    }

    @Test
    public void addToDatabase() {
        ProductCategory category = new ProductCategory(random.nextInt(666666), "category name", "department", "category description");
        int sizeBefore = categoryDaoDb.getAll().size();

        categoryDaoDb.add(category);
        assertEquals(sizeBefore + 1, categoryDaoDb.getAll().size());
    }

    @Test
    public void deleteFromDatabase() {
        ProductCategory categoryToDelete = new ProductCategory(random.nextInt(666666), "delete this", "delete this", "delete this");
        categoryDaoDb.add(categoryToDelete);
        int sizeBeforeDelete = categoryDaoDb.getAll().size();

        categoryDaoDb.remove(categoryToDelete.getId());
        assertEquals(sizeBeforeDelete - 1, categoryDaoDb.getAll().size());
    }
}