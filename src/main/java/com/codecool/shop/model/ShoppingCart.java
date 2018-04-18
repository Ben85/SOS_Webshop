package com.codecool.shop.model;

import com.codecool.shop.dao.implementation.ProductDaoMem;

import java.util.HashMap;

public class ShoppingCart {

    private HashMap<Product, Integer> itemList;
    private int id;
    private static int counter = 0;

    public ShoppingCart() {
        this.id = ++counter;
        this.itemList = new HashMap<>();
    }

    public void changeItemQuantity(int id) {
        Product item = ProductDaoMem.getInstance().find(id);
        if (!this.itemList.containsKey(item)) {
            this.itemList.put(item, 1);
        } else {
            this.itemList.put(item, this.itemList.get(item) + 1);
        }
    }

    public void changeItemQuantity(int id, int quantity) {
        Product item = ProductDaoMem.getInstance().find(id);
        if (quantity == 0) {
            this.itemList.remove(item);
        } else {
            this.itemList.put(item, quantity);
        }
    }

    public int getId() {
        return id;
    }

    public HashMap<Product, Integer> getItemList() {
        return this.itemList;
    }

    public void setItemList(HashMap<Product, Integer> itemList) {
        this.itemList = itemList;
    }

}
