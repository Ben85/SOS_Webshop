package com.codecool.shop.dao.implementation;

import com.codecool.shop.model.Customer;
import org.junit.jupiter.api.Test;

import java.util.Random;

import static org.junit.jupiter.api.Assertions.assertEquals;

class CustomerDaoDatabaseTest {
    private Random random = new Random();

    @Test
    public void addToDatabase() {
        Customer customer = new Customer(
                "first name",
                "last name",
                "password",
                "email",
                666666,
                "5411",
                "city",
                "street",
                "6522",
                "city",
                "street",
                "username" + random.nextInt(1000)
        );
        CustomerDaoDatabase customerDaoDb = CustomerDaoDatabase.getInstance();
        int sizeBefore = customerDaoDb.getAll().size();

        customerDaoDb.add(customer);
        assertEquals(sizeBefore + 1, customerDaoDb.getAll().size());
    }

//    @Test
//    public void deleteFromDatabase() {
//        Supplier supplierToDelete = new Supplier("delete this", "delete this");
//        SupplierDaoDatabase supplierDaoDatabase = SupplierDaoDatabase.getInstance();
//        supplierDaoDatabase.add(supplierToDelete);
//        int sizeBeforeDelete = supplierDaoDatabase.getAll().size();
//
//        supplierDaoDatabase.remove(supplierToDelete.getId());
//        assertEquals(sizeBeforeDelete - 1, supplierDaoDatabase.getAll().size());
//    }
}