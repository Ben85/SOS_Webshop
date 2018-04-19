package com.codecool.shop.paypal;

import com.codecool.shop.helper.HTTPRequestHelper;
import org.json.simple.JSONArray;
import org.json.simple.JSONAware;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import java.io.IOException;
import java.lang.reflect.Field;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.Map;

public class PaypalTypeStructures {

    @SuppressWarnings("unchecked")
    private static class PayPalTypeStructures {
        private interface BaseJSONType {
            JSONAware toJSON();
        }

        private static JSONArray verifyJSONArray(JSONArray jsonArray) {
            return new JSONArray() {{
                for (Object value : jsonArray) {
                    if (value != null) {
                        add(value);
                    }
                }
            }};
        }

        private static JSONObject verifyJSONObject(JSONObject jsonObject) {
            return new JSONObject() {{
                for (Object key : jsonObject.keySet()) {
                    Object value = jsonObject.get(key);
                    if (value != null) {
                        put(key, value);
                    }
                }
            }};
        }

        private static abstract class BaseStructureType implements BaseJSONType {
            public String getProperty(String fieldName) throws NoSuchFieldException {
                try {
                    return (String) this.getClass().getDeclaredField(fieldName).get(this);
                }
                catch (IllegalAccessException ignore) {
                    return null;
                }
            }

            public abstract JSONAware toJSON();
        }

        public static class Item extends BaseStructureType {
            private String quantity;
            private String name;
            private String price;
            private String currency;
            private String description;
            private String tax;

            @Override
            public JSONAware toJSON() {
                return verifyJSONObject(new JSONObject() {{
                    for (Field field : this.getClass().getDeclaredFields()) {
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
                String price,
                String currency,
                String description,
                Float tax
            ) {
                this.quantity = String.valueOf(quantity);
                this.name = name;
                this.price = String.valueOf(price);
                this.currency = currency;
                this.description = description;
                this.tax = String.valueOf(tax);
            }
        }

        public static class TransactionAmount extends BaseStructureType {
            private String totalAmount;
            private String subtotalAmount;
            private String currency;
            private String tax;
            private String shippingCost;
            private String shippingDiscount;

            @Override
            public JSONAware toJSON() {
                return verifyJSONObject(new JSONObject() {{
                    put("total", totalAmount);
                    put("currency", currency);
                    put("details", verifyJSONObject(new JSONObject() {{
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

        public static class ItemList implements BaseJSONType{
            private LinkedList<Item> itemList;

            public LinkedList<Item> getItemList() {
                return itemList;
            }

            @Override
            public JSONAware toJSON() {
                return verifyJSONArray(new JSONArray() {{
                    addAll(itemList);
                }});
            }

            public ItemList() {
                itemList = new LinkedList<Item>();
            }

            public ItemList(LinkedList<Item> itemList) {
                this.itemList = itemList;
            }
        }

        public static class RedirectURLs extends BaseStructureType {
            private String returnURL;
            private String cancelURL;

            @Override
            public JSONAware toJSON() {
                return new JSONObject() {{
                    put("return_url", returnURL == null ? "" : returnURL);
                    put("cancel_url", cancelURL == null ? "" : cancelURL);
                }};
            }

            public RedirectURLs(String returnURL, String cancelURL) {
                this.returnURL = returnURL;
                this.cancelURL = cancelURL;
            }
        }

        public static class Payer extends BaseStructureType {
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

        public static class Transactions extends BaseStructureType {
            private ItemList itemList;
            private TransactionAmount transactionAmount;

            @Override
            public JSONAware toJSON() {
                return verifyJSONObject(new JSONObject() {{
                    put("amount", transactionAmount.toJSON());
                    put("item_list", verifyJSONObject(new JSONObject() {{
                        put("items", itemList.toJSON());
                    }}));
                }});
            }

            public Transactions(
                ItemList itemList,
                TransactionAmount transactionAmount
            ) {
                this.itemList = itemList;
                this.transactionAmount = transactionAmount;
            }
        }

        public static class PaypalPayment extends BaseStructureType {
            private static final String PAYPAL_API_PAYMENT_URL = "https://api.sandbox.paypal.com/v1/payments/payment";

            private String intent;
            private String experienceProfileId;
            private String description;
            private String invoiceNumber;
            private String customMessage;
            private RedirectURLs redirectURLs;
            private Payer payer;
            private Transactions transactions;

            @Override
            public JSONAware toJSON() {
                return verifyJSONObject(new JSONObject() {{
                    put("intent", intent);
                    put("experience_profile_id", experienceProfileId);
                    put("redirect_urls", redirectURLs.toJSON());
                    put("payer", payer.toJSON());
                    put("transactions", transactions.toJSON());
                    put("description", description);
                    put("invoice_number", invoiceNumber);
                    put("custom", customMessage);
                }});
            }

            public String processPayment(PaypalAuthenticator authenticator)
                throws IOException
            {
                String requestBody = this.toJSON().toJSONString();
                String authorizationValue = "Bearer " + authenticator.generateAccessToken();

                String response = HTTPRequestHelper.getInstance().executePost(
                    PAYPAL_API_PAYMENT_URL,
                    requestBody,
                    new HashMap<String, String>() {{
                        put("Accept","application/json");
                        put("Authorization", authorizationValue);
                    }}
                );

                try {
                    JSONObject jsonResponse = (JSONObject) new JSONParser().parse(response);
                    return (String) jsonResponse.get("id");
                }
                catch (ParseException ignore) {
                    throw new IOException("Failed to parse PayPal authentication json response.");
                }
            }

            public PaypalPayment(
                String intent,
                String experienceProfileId,
                String description,
                String invoiceNumber,
                String customMessage,
                RedirectURLs redirectURLs,
                Payer payer,
                Transactions transactions
            ) {
                this.intent = intent;
                this.experienceProfileId = experienceProfileId;
                this.description = description;
                this.invoiceNumber = invoiceNumber;
                this.customMessage = customMessage;
                this.redirectURLs = redirectURLs;
                this.payer = payer;
                this.transactions = transactions;
            }
        }
    }
}
