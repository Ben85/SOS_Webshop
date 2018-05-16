package com.codecool.paypal;

import com.codecool.paypal.helper.HTTPClient;
import org.json.simple.JSONAware;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import java.io.IOException;
import java.util.HashMap;

@SuppressWarnings("unchecked")
public class PaymentExecutor extends BaseTypes.BaseStructureType {
    private static final String PAYPAL_API_EXECUTE_PAYMENT_URL = "https://api.sandbox.paypal.com/v1/payments/payment/%s/execute/";

    private String payerId;
    private String paymentId;

    private String getFormattedUrl() {
        return String.format(PAYPAL_API_EXECUTE_PAYMENT_URL, paymentId);
    }

    @Override
    public JSONAware toJSON() {
        return new JSONObject() {{
            put("payer_id", payerId);
        }};
    }

    public void executePayment(Authenticator authenticator)
        throws IOException
    {
        String requestBody = this.toJSON().toJSONString();
        String authorizationValue = "Bearer " + authenticator.generateAccessToken();

        String response = HTTPClient.getInstance().executePost(
            getFormattedUrl(),
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

            if (jsonResponse.get("state") == "approved") {
                throw new IOException("Invalid Paypal api response state");
            }
        }
        catch (ParseException ignore) {
            throw new IOException();
        }
    }

    public PaymentExecutor(String payerId, String paymentId) {
        this.payerId = payerId;
        this.paymentId = paymentId;
    }

}
