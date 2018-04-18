package com.codecool.shop.model;

import java.util.HashMap;

public class ShoppingCart {

    private HashMap<Product, Integer> itemList;
    private int id;
    private static int counter = 0;

    public ShoppingCart() {
        this.id = ++counter;
        this.itemList = new HashMap<>();
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
