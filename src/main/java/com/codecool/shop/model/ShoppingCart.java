package com.codecool.shop.model;

import com.codecool.shop.dao.implementation.ProductDaoMem;

import java.util.HashMap;

public class ShoppingCart {


    private int id;
    private static int counter = 0;


    HashMap<Product, Integer> itemList = new HashMap<Product, Integer>();

    public ShoppingCart(){
        this.id = ++counter;
    }

    public ShoppingCart(HashMap<Product, Integer> hashMap){
        this.id = ++counter;
        itemList = (HashMap<Product, Integer>) hashMap.clone();
    }

    public void changeItemQuantity(int id) {
        Product item = ProductDaoMem.getInstance().find(id);
        if (!itemList.containsKey(item)) {
            itemList.put(item, 1);
        } else {
            itemList.put(item, itemList.get(item) + 1);
        }
    }

    public void changeItemQuantity(int id, int quantity) {
        Product item = ProductDaoMem.getInstance().find(id);
        if (quantity == 0) {
            itemList.remove(item);
        } else {
            itemList.put(item, quantity);
        }
    }

    public int getId() {
        return id;
    }

    public HashMap<Product, Integer> getItemList() {
        return itemList;
    }

    public void setItemList(HashMap<Product, Integer> itemList) {
        this.itemList = itemList;
    }

}
