package com.codecool.paypal;


import com.codecool.paypal.helper.JSONVerifier;
import org.json.simple.JSONArray;
import org.json.simple.JSONAware;
import org.json.simple.JSONObject;

@SuppressWarnings("unchecked")
public class Transaction extends BaseTypes.BaseStructureType {
    private String description;
    private String invoiceNumber;
    private String customMessage;
    private ItemList items;
    private TransactionAmount transactionAmount;

    @Override
    public JSONAware toJSON() {
        return JSONVerifier.verifyObject(new JSONObject() {{
            put("amount", transactionAmount.toJSON());
            put("item_list", JSONVerifier.verifyObject(new JSONObject() {{
                put("items", items.toJSON());
            }}));
            put("description", description);
            put("invoice_number", invoiceNumber);
            put("custom", customMessage);
        }});
    }

    public Transaction(
        ItemList items,
        TransactionAmount transactionAmount,
        String description,
        String invoiceNumber,
        String customMessage
    ) {
        this.items = items;
        this.transactionAmount = transactionAmount;
        this.description = description;
        this.invoiceNumber = invoiceNumber;
        this.customMessage = customMessage;
    }
}