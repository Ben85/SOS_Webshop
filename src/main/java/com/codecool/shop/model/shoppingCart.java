package com.codecool.shop.model;

import java.util.HashMap;

public class shoppingCart {


    private int id;
    private static int counter = 0;


    HashMap<Product, Integer> itemList = new HashMap<Product, Integer>();

    shoppingCart(){
        this.id = ++counter;
    }

    shoppingCart(HashMap<Product, Integer> hashMap){
        this.id = ++counter;
        itemList = (HashMap<Product, Integer>) hashMap.clone();
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
