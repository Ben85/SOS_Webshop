package com.codecool.paypal;

import com.codecool.paypal.helper.JSONVerifier;
import org.json.simple.JSONAware;
import org.json.simple.JSONObject;

import java.lang.reflect.Field;

@SuppressWarnings("unchecked")
public class Item extends BaseTypes.BaseStructureType {
    private String quantity;
    private String name;
    private String price;
    private String currency;
    private String description;
    private String tax;

    @Override
    public JSONAware toJSON() {
        Field[] itemFields = this.getClass().getDeclaredFields();

        return JSONVerifier.verifyObject(new JSONObject() {{
            for (Field field : itemFields) {
                try {
                    String fieldName = field.getName();
                    put(fieldName, getProperty(fieldName));
                } catch (NoSuchFieldException ignore) {}
            }
        }});
    }

    public Item(
        int quantity,
        String name,
        float price,
        String currency,
        String description,
        float tax
    ) {
        this.quantity = String.valueOf(quantity);
        this.name = name;
        this.price = String.valueOf(price);
        this.currency = currency;
        this.description = description;
        this.tax = String.valueOf(tax);
    }
}