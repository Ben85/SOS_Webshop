package com.codecool.shop.model;

import com.codecool.shop.dao.implementation.ProductDaoMem;

import java.util.HashMap;
import java.util.Map;

public class ShoppingCart {

    private HashMap<Product, Integer> itemList;
    private int id;
    private static int counter = 0;
    private float sumPrice;

    public ShoppingCart() {
        this.id = ++counter;
        this.itemList = new HashMap<>();
        this.sumPrice = 0;
    }

    public void changeItemQuantity(int id) {
        Product item = ProductDaoMem.getInstance().find(id);
        if (!this.itemList.containsKey(item)) {
            this.itemList.put(item, 1);
        } else {
            this.itemList.put(item, this.itemList.get(item) + 1);
        }
        calculateSumPrice();
    }

    public void changeItemQuantity(int id, int quantity) {
        Product item = ProductDaoMem.getInstance().find(id);
        if (quantity == 0) {
            this.itemList.remove(item);
        } else {
            this.itemList.put(item, quantity);
        }
        calculateSumPrice();
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

    private void calculateSumPrice() {
        for (Map.Entry<Product, Integer> entry : this.itemList.entrySet()) {
            Product key = entry.getKey();
            Integer value = entry.getValue();
            this.sumPrice += key.getDefaultPrice() * value;
        }
    }

    public String getSumPrice() {
        return String.valueOf(this.sumPrice) + " USD";
    }

}
