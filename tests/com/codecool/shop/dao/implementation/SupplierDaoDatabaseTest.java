package com.codecool.shop.dao.implementation;

import com.codecool.shop.model.Supplier;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class SupplierDaoDatabaseTest {
    private Supplier supplier;

    @BeforeEach
    public void init() {
        this.supplier = new Supplier("supplier name", "supplier description");
    }

    @Test
    public void addToDatabase() {
        SupplierDaoDatabase supplierDaoDatabase = SupplierDaoDatabase.getInstance();
        int sizeBefore = supplierDaoDatabase.getAll().size();
        supplierDaoDatabase.add(supplier);

        assertEquals(sizeBefore + 1, supplierDaoDatabase.getAll().size());
    }

    @Test
    public void deleteFromDatabase() {
        SupplierDaoDatabase supplierDaoDatabase = SupplierDaoDatabase.getInstance();
        Supplier supplierToDelete = new Supplier("delete this", "delete this");
        supplierDaoDatabase.add(supplierToDelete);
        int id = supplierToDelete.getId();

        int sizeBeforeDelete = supplierDaoDatabase.getAll().size();
        supplierDaoDatabase.remove(id);

        assertEquals(sizeBeforeDelete - 1, supplierDaoDatabase.getAll().size());
    }
}