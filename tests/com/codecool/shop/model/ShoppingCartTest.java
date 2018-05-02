package com.codecool.shop.model;

import com.codecool.shop.dao.implementation.ProductDaoMem;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class ShoppingCartTest {

    @BeforeEach
    public void initProducts() {
        Supplier supplier = new Supplier("test supplier", "nothing");
        ProductCategory cat = new ProductCategory("name", "dep", "nothing");
        Product prod1 = new Product("test product 1", 100L, "HUF", "nothing", cat, supplier, "12", "red");
        ProductDaoMem.getInstance().add(prod1);
        Product prod2 = new Product("test product 2", 200L, "HUF", "nothing", cat, supplier, "12", "red");
        ProductDaoMem.getInstance().add(prod2);
        Product prod3 = new Product("test product 3", 300L, "HUF", "nothing", cat, supplier, "12", "red");
        ProductDaoMem.getInstance().add(prod3);
    }

    @Test
    public void isCartIsEmpty() {
        ShoppingCart cart = new ShoppingCart();
        assertEquals("0.0 HUF", cart.getSumPrice());
        assertEquals(0, cart.getItemList().size());
    }

    @Test
    public void isCorrectSum() {
        ShoppingCart cart = new ShoppingCart();
        cart.changeItemQuantity(1);
        cart.changeItemQuantity(2);
        assertEquals("300.0 HUF", cart.getSumPrice());
    }

    @Test
    public void isCorrectQuantity() {
        Supplier supplier = new Supplier("test supplier", "nothing");
        ProductCategory cat = new ProductCategory("name", "dep", "nothing");
        Product prod4 = new Product("test product 4", 100L, "HUF", "nothing", cat, supplier, "12", "red");
        ProductDaoMem.getInstance().add(prod4);

        ShoppingCart cart = new ShoppingCart();
        cart.changeItemQuantity(4);
        assertEquals(1, cart.getItemList().size());
        cart.changeItemQuantity(2);
        assertEquals(2, cart.getItemList().size());
        cart.changeItemQuantity(4);
        assertEquals((Integer)2, cart.getItemList().get(prod4));
        cart.changeItemQuantity(4, 0);
        assertEquals(null, cart.getItemList().get(prod4));
        cart.changeItemQuantity(4, 4);
        assertEquals((Integer)4, cart.getItemList().get(prod4));
    }

    @Test
    public void isCartIdReturned() {
        ShoppingCart cart = new ShoppingCart();
        assertEquals(4, cart.getId());
    }

}