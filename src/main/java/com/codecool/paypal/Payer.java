package com.codecool.paypal;

import org.json.simple.JSONAware;
import org.json.simple.JSONObject;

@SuppressWarnings("unchecked")
public class Payer extends BaseTypes.BaseStructureType {
    private String paymentMethod;

    @Override
    public JSONAware toJSON() {
        return new JSONObject() {{
            put("payment_method", paymentMethod == null ? "" : paymentMethod);
        }};
    }

    public Payer(String paymentMethod) {
        this.paymentMethod = paymentMethod;
    }
}