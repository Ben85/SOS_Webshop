package com.codecool.paypal;

import com.codecool.paypal.basetypes.BaseStructure;
import com.codecool.paypal.helper.HTTPClient;
import com.codecool.paypal.helper.JSONVerifier;
import org.json.simple.JSONAware;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import java.io.IOException;
import java.util.HashMap;

@SuppressWarnings("unchecked")
public class Payment extends BaseStructure {
    private static final String PAYPAL_API_CREATE_PAYMENT_URL = "https://api.sandbox.paypal.com/v1/payments/payment";

    private String intent;
    private String experienceProfileId;
    private String noteToPayer;
    private RedirectURLs redirectURLs;
    private Payer payer;
    private TransactionList transactions;

    @Override
    public JSONAware toJSON() {
        return JSONVerifier.verifyObject(new JSONObject() {{
            put("intent", intent);
            put("experience_profile_id", experienceProfileId);
            put("note_to_payer", noteToPayer);
            put("redirect_urls", redirectURLs.toJSON());
            put("payer", payer.toJSON());
            put("transactions", transactions.toJSON());
        }});
    }

    public String processPayment(Authenticator authenticator)
        throws IOException
    {
        String requestBody = this.toJSON().toJSONString();
        String authorizationValue = "Bearer " + authenticator.generateAccessToken();

        String response = HTTPClient.getInstance().executePost(
            PAYPAL_API_CREATE_PAYMENT_URL,
            requestBody,
            new HashMap<String, String>() {{
                put("Accept","application/json");
                put("Accept-Language", "en_US");
                put("Content-Type","application/json; charset=utf-8");
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

    public Payment(
        String intent,
        String experienceProfileId,
        String noteToPayer,
        RedirectURLs redirectURLs,
        Payer payer,
        TransactionList transactions
    ) {
        this.intent = intent;
        this.experienceProfileId = experienceProfileId;
        this.noteToPayer = noteToPayer;
        this.redirectURLs = redirectURLs;
        this.payer = payer;
        this.transactions = transactions;
    }
}
