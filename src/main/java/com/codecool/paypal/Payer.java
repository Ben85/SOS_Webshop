package com.codecool.paypal;

import com.codecool.paypal.basetypes.BaseStructure;
import org.json.simple.JSONAware;
import org.json.simple.JSONObject;

@SuppressWarnings("unchecked")
public class Payer extends BaseStructure {
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