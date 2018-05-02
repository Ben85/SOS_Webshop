package com.codecool.paypal;

import com.codecool.paypal.helper.HTTPClient;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import java.io.IOException;
import java.util.HashMap;

public class Authenticator {
    private static final String PAYPAL_API_OAUTH2_URL = "https://api.sandbox.paypal.com/v1/oauth2/token";
    private AuthenticationCredentials authenticationCredentials;

    public String generateAccessToken() throws IOException {
        String authorizationValue = "Basic " + authenticationCredentials.getEncodedCredentials();

        String response = HTTPClient.getInstance().executePost(
            PAYPAL_API_OAUTH2_URL,
            "grant_type=client_credentials",
            new HashMap<String, String>() {{
                put("Accept","application/json");
                put("Accept-Language", "en_US");
                put("Authorization", authorizationValue);
            }}
        );

        try {
            JSONObject jsonResponse = (JSONObject) new JSONParser().parse(response);
            return (String) jsonResponse.get("access_token");
        }
        catch (ParseException ignore) {
            throw new IOException("Failed to parse PayPal authentication json response.");
        }
    }

    public Authenticator(AuthenticationCredentials authenticationCredentials) {
        this.authenticationCredentials = authenticationCredentials;
    }
}
