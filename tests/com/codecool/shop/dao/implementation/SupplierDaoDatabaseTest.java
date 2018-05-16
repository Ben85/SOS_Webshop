package com.codecool.shop.dao.implementation;

import com.codecool.shop.model.Supplier;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class SupplierDaoDatabaseTest {

    @Test
    public void addToDatabase() {
        Supplier supplier = new Supplier("supplier name", "supplier description");
        SupplierDaoDatabase supplierDaoDatabase = SupplierDaoDatabase.getInstance();
        int sizeBefore = supplierDaoDatabase.getAll().size();

        supplierDaoDatabase.add(supplier);
        assertEquals(sizeBefore + 1, supplierDaoDatabase.getAll().size());
    }

    @Test
    public void deleteFromDatabase() {
        Supplier supplierToDelete = new Supplier("delete this", "delete this");
        SupplierDaoDatabase supplierDaoDatabase = SupplierDaoDatabase.getInstance();
        supplierDaoDatabase.add(supplierToDelete);
        int sizeBeforeDelete = supplierDaoDatabase.getAll().size();

        supplierDaoDatabase.remove(supplierToDelete.getId());
        assertEquals(sizeBeforeDelete - 1, supplierDaoDatabase.getAll().size());
    }
}