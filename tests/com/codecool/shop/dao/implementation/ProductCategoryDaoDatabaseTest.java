package com.codecool.shop.dao.implementation;

import com.codecool.shop.model.ProductCategory;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class ProductCategoryDaoDatabaseTest {
    private ProductCategory category;

    @BeforeEach
    public void init() {
        this.category = new ProductCategory("category name", "department", "category description");
    }

    @Test
    public void addToDatabase() {
        ProductCategoryDaoDatabase categoryDaoDatabase = ProductCategoryDaoDatabase.getInstance();
        int sizeBefore = categoryDaoDatabase.getAll().size();
        categoryDaoDatabase.add(category);

        assertEquals(sizeBefore + 1, categoryDaoDatabase.getAll().size());
    }

//    @Test
//    public void deleteFromDatabase() {
//        SupplierDaoDatabase supplierDaoDatabase = SupplierDaoDatabase.getInstance();
//        Supplier supplierToDelete = new Supplier("delete this", "delete this");
//        supplierDaoDatabase.add(supplierToDelete);
//        int id = supplierToDelete.getId();
//
//        int sizeBeforeDelete = supplierDaoDatabase.getAll().size();
//        supplierDaoDatabase.remove(id);
//
//        assertEquals(sizeBeforeDelete - 1, supplierDaoDatabase.getAll().size());
//    }
}