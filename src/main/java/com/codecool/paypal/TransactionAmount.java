package com.codecool.paypal;

import com.codecool.paypal.basetypes.BaseStructure;
import com.codecool.paypal.helper.JSONVerifier;
import org.json.simple.JSONAware;
import org.json.simple.JSONObject;

@SuppressWarnings("unchecked")
public class TransactionAmount extends BaseStructure {
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
        String subtotalAmount,
        String currency,
        String tax,
        String shippingCost,
        String shippingDiscount,
        String totalAmount
    ) {
        this.subtotalAmount = subtotalAmount;
        this.currency = currency;
        this.tax = tax;
        this.shippingCost = shippingCost;
        this.shippingDiscount = shippingDiscount;
        this.totalAmount = totalAmount;
    }

    public TransactionAmount(
        float subtotalAmount,
        Currencies currency,
        float tax,
        float shippingCost,
        float shippingDiscount
    ) {
        this(
            String.valueOf(subtotalAmount),
            currency.toString(),
            String.valueOf(tax),
            String.valueOf(shippingCost),
            String.valueOf(shippingDiscount),
            String.valueOf(subtotalAmount + tax + shippingDiscount)
        );
    }
}