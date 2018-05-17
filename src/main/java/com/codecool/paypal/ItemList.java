package com.codecool.paypal;

import com.codecool.paypal.helper.JSONVerifier;
import org.json.simple.JSONArray;
import org.json.simple.JSONAware;

import java.util.LinkedList;

@SuppressWarnings("unchecked")
public class ItemList implements BaseTypes.BaseJSONType {
    private LinkedList<Item> itemList;

    public LinkedList<Item> getItemList() {
        return itemList;
    }

    @Override
    public JSONAware toJSON() {
        return JSONVerifier.verifyArray(new JSONArray() {{
            for (Item item : itemList) {
                add(item.toJSON());
            }
        }});
    }

    public float getTotalTaxValue() {
        float totalTaxValue = 0.0f;

        for (Item item : itemList) {
            try {
                int quantity = Integer.parseInt(item.getProperty("quantity"));
                float value = Float.parseFloat(item.getProperty("tax"));

                totalTaxValue += value * quantity;
            } catch (NoSuchFieldException ignore) {}
        }

        return totalTaxValue;
    }

    public float getTotalItemValue() {
        float totalItemValue = 0.0f;

        for (Item item : itemList) {
            try {
                int quantity = Integer.parseInt(item.getProperty("quantity"));
                float value = Float.parseFloat(item.getProperty("price"));

                totalItemValue += value * quantity;
            } catch (NoSuchFieldException ignore) {}
        }

        return totalItemValue;
    }

    public ItemList() {
        itemList = new LinkedList<Item>();
    }

    public ItemList(LinkedList<Item> itemList) {
        this.itemList = itemList;
    }
}