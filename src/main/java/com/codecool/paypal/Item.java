package com.codecool.paypal;

import com.codecool.paypal.basetypes.BaseStructure;
import com.codecool.paypal.helper.JSONVerifier;
import org.json.simple.JSONAware;
import org.json.simple.JSONObject;

import java.lang.reflect.Field;

@SuppressWarnings("unchecked")
public class Item extends BaseStructure {
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
        String quantity,
        String name,
        String price,
        String currency,
        String description,
        String tax
    ) {
        this.quantity = quantity;
        this.name = name;
        this.price = price;
        this.currency = currency;
        this.description = description;
        this.tax = tax;
    }

    public Item(
        int quantity,
        String name,
        float price,
        Currencies currency,
        String description,
        float tax
    ) {
        this(
            String.valueOf(quantity),
            name,
            String.valueOf(price),
            currency.toString(),
            description,
            String.valueOf(tax)
        );
    }
}