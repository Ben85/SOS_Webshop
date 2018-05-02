package com.codecool.paypal;

import com.codecool.paypal.helper.JSONVerifier;
import org.json.simple.JSONAware;
import org.json.simple.JSONObject;

@SuppressWarnings("unchecked")
public class TransactionAmount extends BaseTypes.BaseStructureType {
    private String totalAmount;
    private String subtotalAmount;
    private String currency;
    private String tax;
    private String shippingCost;
    private String shippingDiscount;

    @Override
    public JSONAware toJSON() {
        return JSONVerifier.verifyObject(new JSONObject() {{
            put("total", totalAmount);
            put("currency", currency);
            put("details", JSONVerifier.verifyObject(new JSONObject() {{
                put("subtotal", subtotalAmount);
                put("shipping", shippingCost);
                put("tax", tax);
                put("shipping_discount", shippingDiscount);
            }}));
        }});
    }

    public TransactionAmount(
        float subtotalAmount,
        String currency,
        float tax,
        float shippingCost,
        float shippingDiscount
    ) {
        this.subtotalAmount = String.valueOf(subtotalAmount);
        this.currency = currency;
        this.tax = String.valueOf(tax);
        this.shippingCost = String.valueOf(shippingCost);
        this.shippingDiscount = String.valueOf(shippingDiscount);
        this.totalAmount = String.valueOf(subtotalAmount + tax + shippingDiscount);
    }
}