package com.codecool.shop.dao.implementation;

import com.codecool.shop.model.ProductCategory;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class ProductCategoryDaoDatabaseTest {

    @Test
    public void addToDatabase() {
        ProductCategory category = new ProductCategory("category name", "department", "category description");
        ProductCategoryDaoDatabase categoryDaoDatabase = ProductCategoryDaoDatabase.getInstance();
        int sizeBefore = categoryDaoDatabase.getAll().size();

        categoryDaoDatabase.add(category);
        assertEquals(sizeBefore + 1, categoryDaoDatabase.getAll().size());
    }

    @Test
    public void deleteFromDatabase() {
        ProductCategory categoryToDelete = new ProductCategory("delete this", "delete this", "delete this");
        ProductCategoryDaoDatabase productCategoryDaoDatabase = ProductCategoryDaoDatabase.getInstance();
        productCategoryDaoDatabase.add(categoryToDelete);
        int sizeBeforeDelete = productCategoryDaoDatabase.getAll().size();

        productCategoryDaoDatabase.remove(categoryToDelete.getId());
        assertEquals(sizeBeforeDelete - 1, productCategoryDaoDatabase.getAll().size());
    }
}