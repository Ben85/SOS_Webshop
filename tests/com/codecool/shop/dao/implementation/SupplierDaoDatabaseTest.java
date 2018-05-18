package com.codecool.shop.dao.implementation;

import com.codecool.shop.model.Supplier;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class SupplierDaoDatabaseTest {

    private SupplierDaoDatabase supplierDaoDb;

    @BeforeEach
    void setUp() {
        supplierDaoDb = SupplierDaoDatabase.getInstance();
    }

    @Test
    public void addToDatabase() {
        Supplier supplier = new Supplier("supplier name", "supplier description");
        int sizeBefore = supplierDaoDb.getAll().size();

        supplierDaoDb.add(supplier);
        assertEquals(sizeBefore + 1, supplierDaoDb.getAll().size());
    }

    @Test
    public void deleteFromDatabase() {
        Supplier supplierToDelete = new Supplier("delete this", "delete this");
        supplierDaoDb.add(supplierToDelete);
        int sizeBeforeDelete = supplierDaoDb.getAll().size();

        supplierDaoDb.remove(supplierToDelete.getId());
        assertEquals(sizeBeforeDelete - 1, supplierDaoDb.getAll().size());
    }
}