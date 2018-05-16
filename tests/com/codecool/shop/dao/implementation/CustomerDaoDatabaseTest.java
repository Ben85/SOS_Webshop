package com.codecool.shop.dao.implementation;

import com.codecool.shop.model.Customer;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Random;

import static org.junit.jupiter.api.Assertions.assertEquals;

class CustomerDaoDatabaseTest {

    private CustomerDaoDatabase customerDaoDb;
    private Random random = new Random();

    @BeforeEach
    void setUp() {
        customerDaoDb = CustomerDaoDatabase.getInstance();
    }

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
        int sizeBefore = customerDaoDb.getAll().size();

        customerDaoDb.add(customer);
        assertEquals(sizeBefore + 1, customerDaoDb.getAll().size());
    }

    @Test
    public void deleteFromDatabase() {
        Customer customerToDelete = new Customer(
                "delete this",
                "delete this",
                "delete this",
                "delete this",
                666666,
                "delete this",
                "delete this",
                "delete this",
                "delete this",
                "delete this",
                "delete this",
                "delete this " + random.nextInt(1000)
        );
        customerDaoDb.add(customerToDelete);
        int sizeBeforeDelete = customerDaoDb.getAll().size();

        customerDaoDb.remove(customerToDelete.getId());
        assertEquals(sizeBeforeDelete - 1, customerDaoDb.getAll().size());
    }
}